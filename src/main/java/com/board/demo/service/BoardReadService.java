package com.board.demo.service;

import com.board.demo.dto.response.read.BoardReadResponseDTO;
import com.board.demo.dto.response.read.CommentPostResponseDTO;

public interface BoardReadService {
    BoardReadResponseDTO getBoardDetail(int boardId);

    CommentPostResponseDTO postComment(CommentPostResponseDTO commentPostResponseDTO);

}
