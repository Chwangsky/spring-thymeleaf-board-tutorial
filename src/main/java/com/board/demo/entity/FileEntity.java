package com.board.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {
    private int filesId;
    private String attachType;
    private int byteSize;
    private String uuidName;
    private String orgName;
    private String fileDir;
    private int boardId;
}
