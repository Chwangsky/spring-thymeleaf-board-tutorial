package com.board.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.demo.dto.request.search.BoardSearchRequestDTO;
import com.board.demo.dto.response.search.BoardSearchResponseDTO;
import com.board.demo.service.BoardSearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/boards/free")
public class ListController {

        @Autowired
        private BoardSearchService boardSearchService;

        @GetMapping("/list")
        public String showBoardList(BoardSearchRequestDTO boardSearchDTO, Model model) {
                log.info("보드 목록을 보여줍니다.");

                BoardSearchResponseDTO boardSearchResponseDTO = boardSearchService.searchBoards(boardSearchDTO);

                model.addAttribute("dto", boardSearchResponseDTO);

                return "boards/free/list";
        }

}
