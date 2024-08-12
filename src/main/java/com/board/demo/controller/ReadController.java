package com.board.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.demo.dto.request.read.PostCommentRequestDTO;
import com.board.demo.dto.response.list.BoardListResponseDTO;
import com.board.demo.dto.response.read.BoardReadResponseDTO;
import com.board.demo.service.BoardReadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/boards/free/views")
public class ReadController {

    @Autowired
    private BoardReadService boardReadService;

    @GetMapping("/{boardId}")
    public String getBoardDetail(@PathVariable("boardId") Integer boardId, Model model) {
        BoardReadResponseDTO boardReadResponseDTO = boardReadService.getBoardDetail(boardId);

        model.addAttribute("dto", boardReadResponseDTO);

        return "/boards/free/read";
    }

    @PostMapping
    public String postComment(PostCommentRequestDTO postCommentRequestDTO) {

        int boardId = boardReadService.postComment(postCommentRequestDTO);

        return "redirect:/boards/free/views/" + String.valueOf(boardId);
    }

}
