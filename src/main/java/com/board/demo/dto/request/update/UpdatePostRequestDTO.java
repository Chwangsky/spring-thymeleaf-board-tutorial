package com.board.demo.dto.request.update;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostRequestDTO {
    Integer boardId;
    String title;
    String password;
    String writer;
    String content;

    String removeFiles; // removeFiles="1,2,3"과 같은 방식으로 넘어온다. 1, 2, 3은 삭제할 file의 id를 의미 TODO

    MultipartFile[] newFiles;
}