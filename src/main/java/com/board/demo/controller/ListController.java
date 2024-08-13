package com.board.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.demo.dto.request.list.BoardListRequestDTO;
import com.board.demo.dto.response.list.BoardListResponseDTO;
import com.board.demo.service.BoardListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/boards/free/list")
public class ListController {

    private final BoardListService boardListService;

    public ListController(BoardListService boardListService) {
        this.boardListService = boardListService;
    }

    @GetMapping
    public String showBoardList(BoardListRequestDTO boardSearchDTO, Model model) {

        BoardListResponseDTO boardListResponseDTO = boardListService.searchBoards(boardSearchDTO);

        model.addAttribute("dto", boardListResponseDTO);

        return "boards/free/list";
    }

}
