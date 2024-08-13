package com.board.demo.service;

import com.board.demo.dto.request.write.BoardPostBoardRequestDTO;
import com.board.demo.dto.response.write.BoardWriteResponseDTO;

public interface BoardWriteService {

    BoardWriteResponseDTO getWriteForm();

    Integer postBoard(BoardPostBoardRequestDTO dto);

}
