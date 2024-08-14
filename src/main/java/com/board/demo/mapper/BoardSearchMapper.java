package com.board.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.board.demo.entity.BoardSearchEntity;
import com.board.demo.entity.CategoryIdNameEntity;

@Mapper
public interface BoardSearchMapper {
        List<BoardSearchEntity> boardSearch(

                        @Param("regDateStart") String regDateStart,
                        @Param("regDateEnd") String regDateEnd,
                        @Param("categoryName") String categoryName,

                        @Param("keyword") String keyword,
                        @Param("limit") int limit, @Param("offset") int offset);

        int boardSearchCount(@Param("regDateStart") String regDateStart,
                        @Param("regDateEnd") String regDateEnd,
                        @Param("categoryName") String categoryName,

                        @Param("keyword") String keyword);

        @Select("SELECT category_id as categoryId, name FROM category")
        List<CategoryIdNameEntity> findAllCategoryIdsAndNames();

}
