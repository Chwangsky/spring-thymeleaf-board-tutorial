package com.board.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.board.demo.dto.request.delete.BoardDeleteRequestDTO;
import com.board.demo.mapper.BoardDeleteMapper;

@Service
public class BoardDeleteServiceImpl implements BoardDeleteService {

    private final BoardDeleteMapper mapper;

    public BoardDeleteServiceImpl(BoardDeleteMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> deleteBoard(BoardDeleteRequestDTO boardDeleteRequestDTO) {
        Integer boardId = boardDeleteRequestDTO.getBoardId();
        String password = boardDeleteRequestDTO.getPassword();

        String foundPassword = mapper.findPasswordByBoardId(boardId);

        if (!password.equals(foundPassword)) {
            // 비밀번호 불일치 로직
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Incorrect password");
        }

        Integer count = mapper.deleteUserById(boardId);
        if (count == 0) {
            // 삭제 실패 로직
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: Failed to delete the board.");
        } else {
            // 삭제 성공 로직
            return ResponseEntity.ok("Board deleted successfully.");
        }
    }
}
