package com.board.demo.dto.request.write;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPostBoardRequestDTO {

    Integer categoryId;
    String writer;
    String password;
    String title;
    String content;
    MultipartFile[] files;

}
