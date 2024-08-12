package com.board.demo.dto.request.search;

import io.micrometer.common.lang.Nullable;
import lombok.Getter;

@Getter
public class BoardSearchRequestDTO {

    @Nullable
    private String regDateStart = "1900-01-01";

    @Nullable
    private String regDateEnd = "2100-12-31";

    @Nullable
    private String categoryName = null;

    @Nullable
    private String keyWord = null;

    @Nullable
    private Integer page = 1;
}
