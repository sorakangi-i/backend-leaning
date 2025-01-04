package org.study.board.service;

import org.study.board.domain.BoardAttachmentVO;
import org.study.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public void create(BoardDTO board);
    public boolean update(BoardDTO board);
    public boolean delete(Long no);

    // 첨부 파일
    public BoardAttachmentVO getAttachment(Long no);
    public boolean deleteAttachment(Long no);
}
