package org.study.board.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.study.board.domain.BoardVO;
import org.study.config.RootConfig;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Log4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class} )
class BoardMapperTest {
    @Autowired
    private BoardMapper mapper;

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    void getList() {
        for(BoardVO board : mapper.getList()) {
            log.info(board);
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    void get() {
        BoardVO board = mapper.get(1L);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper에 새 글 추가")
    void create() {
        BoardVO board = new BoardVO();
        board.setTitle("test로 새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("Tester1");

        mapper.create(board);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper에 기존 게시글 수정")
    void update() {
        BoardVO board = new BoardVO();
        board.setNo(2L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("Tester2");

        int count = mapper.update(board);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    @DisplayName("BoardMapper에 게시글 삭제")
    void delete() {
        log.info("DELETE COUNT: " + mapper.delete(2L));
    }
}