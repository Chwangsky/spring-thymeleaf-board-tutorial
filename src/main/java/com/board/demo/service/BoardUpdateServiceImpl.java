package com.board.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.demo.dto.response.update.UpdateResponseDTO;
import com.board.demo.entity.BoardUpdateDetailEntity;
import com.board.demo.entity.FileEntity;
import com.board.demo.mapper.BoardUpdateMapper;

@Service
public class BoardUpdateServiceImpl implements BoardUpdateService {

    private final BoardUpdateMapper mapper;

    public BoardUpdateServiceImpl(BoardUpdateMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UpdateResponseDTO getupdate(int boardId) {

        BoardUpdateDetailEntity boardDetailEntity = mapper.selectBoardDetailById(boardId);
        List<FileEntity> fileEntities = mapper.selectFilesByBoardId(boardId);

        return UpdateResponseDTO.fromEntities(boardDetailEntity, fileEntities);
    }

}
