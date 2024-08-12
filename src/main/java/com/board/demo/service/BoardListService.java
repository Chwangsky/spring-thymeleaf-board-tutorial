package com.board.demo.service;

import com.board.demo.dto.request.search.BoardListRequestDTO;
import com.board.demo.dto.response.search.BoardListResponseDTO;

public interface BoardListService {
    BoardListResponseDTO searchBoards(BoardListRequestDTO boardSearchDTO);
}