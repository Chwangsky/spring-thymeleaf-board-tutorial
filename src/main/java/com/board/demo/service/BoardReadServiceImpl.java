package com.board.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.demo.dto.request.read.CommentPostRequestDTO;
import com.board.demo.dto.response.read.BoardReadResponseDTO;
import com.board.demo.dto.response.read.CommentPostResponseDTO;
import com.board.demo.entity.BoardDetailEntity;
import com.board.demo.entity.CommentEntity;
import com.board.demo.entity.FileEntity;
import com.board.demo.mapper.BoardReadMapper;

public class BoardReadServiceImpl implements BoardReadService {

    @Autowired
    private BoardReadMapper mapper;

    @Override
    public BoardReadResponseDTO getBoardDetail(int boardId) {

        mapper.incrementViewCount(boardId);

        BoardDetailEntity boardDetailEntity = mapper.selectBoardDetailById(boardId);
        List<CommentEntity> commentEntities = mapper.selectCommentsByBoardId(boardId);
        List<FileEntity> fileEntities = mapper.selectFilesByBoardId(boardId);

        return BoardReadResponseDTO.fromEntities(
                boardDetailEntity, commentEntities, fileEntities);

    }

    @Override
    public CommentPostResponseDTO postComment(CommentPostRequestDTO commentPostRequestDTO) {
        Integer boardId = commentPostRequestDTO.getBoardId();
        String content = commentPostRequestDTO.getContent();
        String writer = commentPostRequestDTO.getWriter();

        mapper.insertComment(boardId, writer, content);

    }

}
