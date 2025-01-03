package org.study.board.service;

import org.study.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public void create(BoardDTO board);
    public boolean update(BoardDTO board);
    public boolean delete(Long no);
}
