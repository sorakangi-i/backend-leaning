package org.study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.study.board.domain.BoardAttachmentVO;
import org.study.board.domain.BoardVO;
import org.study.board.dto.BoardDTO;
import org.study.board.mapper.BoardMapper;
import org.study.common.util.UploadFiles;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.study.common.util.UploadFiles.upload;

@Log4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    final private BoardMapper mapper;
    private final static String BASE_DIR = "c:/upload/springboard";

    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");
        return mapper.getList().stream()
                .map(BoardDTO::of)
                .toList();
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get..........");

        BoardDTO board = BoardDTO.of(mapper.get(no));
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public void create(BoardDTO board) {
        log.info("create........." + board);
        BoardVO boardVO = board.toVo();
        mapper.create(boardVO);

        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(boardVO.getNo(), files);
        }
    }

    // 첨부파일 upload
    private void upload(Long bno, List<MultipartFile> files) {
        for(MultipartFile part: files) {
            if(part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update........." + board);
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        BoardVO board = mapper.get(no);
        if (board == null) {
            return false;
        }
        log.info("delete.........." + no);
        return mapper.delete(no) == 1;
    }

    // 첨부파일 1개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }
}
