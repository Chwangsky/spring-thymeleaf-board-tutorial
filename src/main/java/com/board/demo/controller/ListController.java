package com.board.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.demo.dto.request.search.BoardListRequestDTO;
import com.board.demo.dto.response.search.BoardListResponseDTO;
import com.board.demo.service.BoardListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/boards/free/list")
public class ListController {

        @Autowired
        private BoardListService boardListService;

        @GetMapping
        public String showBoardList(BoardListRequestDTO boardSearchDTO, Model model) {
                log.info("보드 목록을 보여줍니다.");

                BoardListResponseDTO boardListResponseDTO = boardListService.searchBoards(boardSearchDTO);

                model.addAttribute("dto", boardListResponseDTO);

                return "boards/free/list";
        }

}
