package com.board.demo.dto.response.search;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardSearchResponseDTO {
    private int totalCount;
    private List<BoardDetailResponseItem> boardDetailResponseItems;
    private PaginationDTO paginationDto;
    private List<CategoryIdNameItem> categoryIdNameItems;
}
