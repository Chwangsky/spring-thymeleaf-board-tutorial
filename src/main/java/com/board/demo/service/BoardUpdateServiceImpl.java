package com.board.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.demo.dto.request.update.UpdatePostRequestDTO;
import com.board.demo.dto.response.update.UpdateResponseDTO;
import com.board.demo.entity.BoardUpdateDetailEntity;
import com.board.demo.entity.FileEntity;
import com.board.demo.entity.FileInsertEntity;
import com.board.demo.exception.FileWriteException;
import com.board.demo.mapper.BoardUpdateMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardUpdateServiceImpl implements BoardUpdateService {

    private final BoardUpdateMapper mapper;
    private final String uploadDirectory;

    public BoardUpdateServiceImpl(BoardUpdateMapper mapper,
            @Value("${upload.directory}") String uploadDirectory) {
        this.mapper = mapper;
        this.uploadDirectory = uploadDirectory;
    }

    @Override
    public UpdateResponseDTO getupdate(int boardId) {

        BoardUpdateDetailEntity boardDetailEntity = mapper.selectBoardDetailById(boardId);
        List<FileEntity> fileEntities = mapper.selectFilesByBoardId(boardId);

        return UpdateResponseDTO.fromEntities(boardDetailEntity, fileEntities);
    }

    @Override
    @Transactional
    public int postUpdate(UpdatePostRequestDTO updatePostRequestDTO) {

        @NotNull
        Integer boardId = updatePostRequestDTO.getBoardId();
        String password = updatePostRequestDTO.getPassword();
        BoardUpdateDetailEntity boardUpdateDetailEntity = mapper.selectBoardDetailById(boardId);
        String storedPassword = boardUpdateDetailEntity.getPassword();

        String title = updatePostRequestDTO.getTitle();
        String content = updatePostRequestDTO.getContent();
        String writer = updatePostRequestDTO.getWriter();

        // 비밀번호가 일치하지 않으면 오류 메시지를 추가하고 다시 수정 페이지로 리다이렉트
        if (!storedPassword.equals(password)) {
            log.info("비밀번호가 일치하지 않습니다.");

            // 비밀번호 불일치 처리 로직
            return 0;
        }

        mapper.updateBoard(boardId, title, content, writer);

        List<FileEntity> fileEntities = mapper.selectFilesByBoardId(boardId);

        String filesToDeleteString = updatePostRequestDTO.getRemoveFiles();
        log.info("0woo filesToDelete", filesToDeleteString);

        if (filesToDeleteString != null && !filesToDeleteString.isEmpty()) {
            String[] filesToDelete = filesToDeleteString.split(",");
            if (filesToDelete != null) {
                for (String file : filesToDelete) {
                    if (!file.trim().isEmpty()) {
                        try {
                            Integer parsedFileId = Integer.parseInt(file.trim());

                            // 1. DB에서 삭제
                            mapper.deleteFileById(parsedFileId);

                        } catch (NumberFormatException e) {
                            log.warn("파일 id 파싱 실패. 파일 id가 잘못 입력되었습니다. 로그를 확인해 주세요");
                        }
                    }
                }
            }
        }

        // 새로운 파일 DB에 정보저장 + 로컬에 저장
        MultipartFile[] newFiles = updatePostRequestDTO.getNewFiles();
        for (MultipartFile file : newFiles) {
            String originalFileName = file.getOriginalFilename();
            String uuidName = UUID.randomUUID().toString();
            Path filePath = Paths.get(uploadDirectory, uuidName);
            Integer fileSize = (int) (long) file.getSize();
            String attachType = file.getContentType();

            if (fileSize == 0)
                continue; // 만약 빈 MultipartFile을 받았을 경우 skip

            try {
                // 디렉토리가 없으면 생성
                Files.createDirectories(Paths.get(uploadDirectory));

                // 파일 저장
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (IOException e) {
                throw new FileWriteException("파일 저장 중 오류가 발생했습니다.", e);
            }

            FileInsertEntity fileEntity = FileInsertEntity.builder()
                    .attachType(attachType)
                    .boardId(boardId)
                    .byteSize(fileSize)
                    .fileDir(filePath.toString())
                    .orgName(originalFileName)
                    .uuidName(uuidName)
                    .build();

            mapper.insertFile(fileEntity);
        }

        // update_date 갱신
        mapper.updateUpdateDate(boardId);

        return boardId;

    }

}