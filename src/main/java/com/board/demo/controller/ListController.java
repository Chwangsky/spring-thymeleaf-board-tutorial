package com.board.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.demo.dto.request.search.BoardSearchRequestDTO;
import com.board.demo.dto.response.search.BoardDetailResponseItem;
import com.board.demo.dto.response.search.BoardSearchResponseDTO;
import com.board.demo.dto.response.search.CategoryIdNameItem;
import com.board.demo.dto.response.search.PaginationDTO;
import com.board.demo.entity.BoardSearchEntity;
import com.board.demo.entity.CategoryIdNameEntity;
import com.board.demo.mapper.BoardSearchMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/boards/free")
public class ListController {

    @Autowired
    BoardSearchMapper mapper;

    private static final int ITEMS_PER_PAGE = 5;

    private static final int PAGE_PER_SECTION = 10;

    // 루트 경로에 대한 요청 처리
    @GetMapping("/babo")
    public String showBoardList(Model model) {
        // 필요한 로직을 여기에 작성
        log.info("보드 목록을 보여줍니다.");

        // 뷰 템플릿 파일을 반환 (예: templates/boards/free/list.html)
        return "boards/free/list";
    }

    @GetMapping("/list")
    public String showBoardList(@RequestParam(required = false) BoardSearchRequestDTO boardSearchDTO, Model model) {
        log.info("보드 목록을 보여줍니다.");

        if (boardSearchDTO == null) {
            boardSearchDTO = new BoardSearchRequestDTO();
            // 다른 기본값 설정 (필요한 경우)
        }

        int page = boardSearchDTO.getPage();
        int offset = (page - 1) * ITEMS_PER_PAGE;
        String regDateStart = boardSearchDTO.getRegDateStart();
        String regDateEnd = boardSearchDTO.getRegDateEnd();
        String keyword = boardSearchDTO.getKeyWord();
        String categoryName = boardSearchDTO.getCategoryName();

        List<BoardSearchEntity> boardSearchEntities = mapper.boardSearch(regDateStart, regDateEnd, categoryName,
                keyword, ITEMS_PER_PAGE, offset);

        List<BoardDetailResponseItem> boardDetailResponseItems = boardSearchEntities.stream()
                .map(BoardDetailResponseItem::fromEntity)
                .collect(Collectors.toList());

        int totalCount = mapper.boardSearchCount(regDateStart, regDateEnd, categoryName, keyword);

        List<CategoryIdNameEntity> categoryEntities = mapper.findAllCategoryIdsAndNames();
        List<CategoryIdNameItem> categoriesItems = categoryEntities.stream()
                .map(CategoryIdNameItem::fromEntity)
                .toList();

        PaginationDTO paginationDto = PaginationDTO.createPaginationDto(totalCount, ITEMS_PER_PAGE,
                PAGE_PER_SECTION, page);

        BoardSearchResponseDTO boardSearchResponseDTO = BoardSearchResponseDTO.builder()
                .totalCount(totalCount)
                .boardDetailResponseItems(boardDetailResponseItems)
                .categoryIdNameItems(categoriesItems)
                .paginationDto(paginationDto)
                .build();

        model.addAttribute("dto", boardSearchResponseDTO);
        return "boards/free/list";
    }

}
