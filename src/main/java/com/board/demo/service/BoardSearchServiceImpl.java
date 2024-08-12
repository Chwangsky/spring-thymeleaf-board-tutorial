package com.board.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.board.demo.dto.request.search.BoardSearchRequestDTO;
import com.board.demo.dto.response.search.BoardDetailResponseItem;
import com.board.demo.dto.response.search.BoardSearchResponseDTO;
import com.board.demo.dto.response.search.CategoryIdNameItem;
import com.board.demo.dto.response.search.PaginationDTO;
import com.board.demo.entity.BoardSearchEntity;
import com.board.demo.entity.CategoryIdNameEntity;
import com.board.demo.mapper.BoardSearchMapper;

@Service
public class BoardSearchServiceImpl implements BoardSearchService {

    @Autowired
    private BoardSearchMapper mapper;

    @Value("${board.items-per-page}")
    private int itemsPerPage;

    @Value("${board.page-per-section}")
    private int pagePerSection;

    @Override
    public BoardSearchResponseDTO searchBoards(BoardSearchRequestDTO boardSearchDTO) {

        int page = boardSearchDTO.getPage() != null ? boardSearchDTO.getPage() : 1;
        int offset = (page - 1) * itemsPerPage;

        String regDateStart = boardSearchDTO.getRegDateStart();
        String regDateEnd = boardSearchDTO.getRegDateEnd();
        String keyword = boardSearchDTO.getKeyword();
        String categoryName = boardSearchDTO.getCategoryName();

        List<BoardSearchEntity> boardSearchEntities = mapper.boardSearch(regDateStart, regDateEnd, categoryName,
                keyword, itemsPerPage, offset);

        List<BoardDetailResponseItem> boardDetailResponseItems = boardSearchEntities.stream()
                .map(BoardDetailResponseItem::fromEntity)
                .collect(Collectors.toList());

        int totalCount = mapper.boardSearchCount(regDateStart, regDateEnd, categoryName, keyword);

        List<CategoryIdNameEntity> categoryEntities = mapper.findAllCategoryIdsAndNames();
        List<CategoryIdNameItem> categoriesItems = categoryEntities.stream()
                .map(CategoryIdNameItem::fromEntity)
                .toList();

        PaginationDTO paginationDto = PaginationDTO.createPaginationDto(totalCount, itemsPerPage,
                pagePerSection, page);

        return BoardSearchResponseDTO.builder()
                .totalCount(totalCount)
                .boardDetailResponseItems(boardDetailResponseItems)
                .categoryIdNameItems(categoriesItems)
                .paginationDto(paginationDto)
                .regDateStart(regDateStart)
                .regDateEnd(regDateEnd)
                .keyword(keyword)
                .categoryName(categoryName)
                .build();
    }
}
