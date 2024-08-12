package com.board.demo.dto.request.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardSearchRequestDTO {

    private String regDateStart; // 초기값이 있으면 @Nullable 불필요

    private String regDateEnd;

    private String categoryName;

    private String keyword;

    private Integer page;
}
