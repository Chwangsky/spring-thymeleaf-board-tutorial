package com.board.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardDeleteMapper {

    @Delete("DELETE FROM board b WHERE board_id = #{boardId}")
    int deleteUserById(@Param("boardId") int boardId);

    @Select("SELECT password from board WHERE board_id = #{boardId}")
    String findPasswordByBoardId(@Param("boardId") int boardId);

}
