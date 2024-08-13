package com.board.demo.service;

import com.board.demo.dto.response.update.UpdateResponseDTO;

public interface BoardUpdateService {

    UpdateResponseDTO getupdate(int boardId);

    // TODO
    // UpdatePostResponseDTO getupdate(UpdatePostRequestDTO)

}
