package com.board.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.board.demo.mapper.BoardSearchMapper;

@Service
public class ListService {

    @Autowired
    BoardSearchMapper boardSearchMapper;



}
