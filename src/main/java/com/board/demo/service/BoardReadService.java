package com.board.demo.service;

import com.board.demo.dto.request.read.PostCommentRequestDTO;
import com.board.demo.dto.response.read.BoardReadResponseDTO;

public interface BoardReadService {
    BoardReadResponseDTO getBoardDetail(int boardId);

    Integer postComment(PostCommentRequestDTO commentPostResponseDTO);

}
