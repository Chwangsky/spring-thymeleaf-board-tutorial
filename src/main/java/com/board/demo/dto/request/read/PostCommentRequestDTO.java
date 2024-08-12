package com.board.demo.dto.request.read;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCommentRequestDTO {
    Integer boardId;
    String writer;
    String content;
}
