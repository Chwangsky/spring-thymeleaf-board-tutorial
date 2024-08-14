package com.board.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.demo.dto.request.delete.BoardDeleteRequestDTO;
import com.board.demo.service.BoardDeleteService;

@RestController
@RequestMapping("/boards/free/delete")
public class DeleteController {

    private final BoardDeleteService boardDeleteService;

    public DeleteController(BoardDeleteService boardDeleteService) {
        this.boardDeleteService = boardDeleteService;
    }

    @PostMapping
    public ResponseEntity<?> getDeleteForm(@RequestBody BoardDeleteRequestDTO boardDeleteRequestDTO) {

        return boardDeleteService.deleteBoard(boardDeleteRequestDTO);
    }

}
