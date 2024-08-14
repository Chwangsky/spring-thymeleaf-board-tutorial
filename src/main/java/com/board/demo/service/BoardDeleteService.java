package com.board.demo.service;

import org.springframework.http.ResponseEntity;

import com.board.demo.dto.request.delete.BoardDeleteRequestDTO;

public interface BoardDeleteService {

    ResponseEntity<?> deleteBoard(BoardDeleteRequestDTO boardDeleteRequestDTO);

}
