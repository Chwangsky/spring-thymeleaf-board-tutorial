package com.board.demo.dto.response.search;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardListResponseDTO {
    private int totalCount;
    private List<BoardDetailResponseItem> boardDetailResponseItems;
    private PaginationDTO paginationDto;
    private List<CategoryIdNameItem> categoryIdNameItems;

    // form 을 계속 저장하면서 가지고 다니기 위한 변수들
    private String regDateStart = LocalDate.now().minusYears(1).toString();
    private String regDateEnd = LocalDate.now().toString();
    private String keyword = "";
    private String categoryName = "";
}
