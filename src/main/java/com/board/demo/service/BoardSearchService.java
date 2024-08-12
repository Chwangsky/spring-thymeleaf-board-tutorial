package com.board.demo.service;

import com.board.demo.dto.request.search.BoardSearchRequestDTO;
import com.board.demo.dto.response.search.BoardSearchResponseDTO;

public interface BoardSearchService {
    BoardSearchResponseDTO searchBoards(BoardSearchRequestDTO boardSearchDTO);
}