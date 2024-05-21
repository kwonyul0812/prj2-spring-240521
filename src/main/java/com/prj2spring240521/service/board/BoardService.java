package com.prj2spring240521.service.board;

import com.prj2spring240521.domain.board.Board;
import com.prj2spring240521.mapper.board.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {
    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }
}
