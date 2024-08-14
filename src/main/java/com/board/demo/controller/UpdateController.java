package com.board.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.demo.dto.request.update.UpdatePostRequestDTO;
import com.board.demo.dto.response.update.UpdateResponseDTO;
import com.board.demo.service.BoardUpdateService;

@Controller
@RequestMapping("/boards/free/modify")
public class UpdateController {

    final BoardUpdateService boardUpdateService;

    public UpdateController(BoardUpdateService boardUpdateService) {
        this.boardUpdateService = boardUpdateService;
    }

    @GetMapping
    public String getModifyForm(@RequestParam("boardId") Integer boardId, Model model) {
        UpdateResponseDTO dto = boardUpdateService.getupdate(boardId);
        model.addAttribute("dto", dto);
        return "/boards/free/update";
    }

    @PostMapping
    public String postModifyForm(UpdatePostRequestDTO updatePostRequestDTO) {
        Integer boardId = boardUpdateService.postUpdate(updatePostRequestDTO);
        return "redirect:/boards/free/views/" + String.valueOf(boardId);
    }

}
