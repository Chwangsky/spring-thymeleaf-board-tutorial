package com.board.demo.dto.request.list;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardListRequestDTO {

    private String regDateStart;

    private String regDateEnd;

    private String categoryName;

    private String keyword;

    private Integer page;
}
