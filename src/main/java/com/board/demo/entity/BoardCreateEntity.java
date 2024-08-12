package com.board.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateEntity {

    private Integer boardId;
    private String title;
    private String writer;
    private int views;
    private int categoryId;
    private String content;
    private String password;
}
