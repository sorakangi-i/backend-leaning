package org.study.board.service;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.study.board.dto.BoardDTO;
import org.study.config.RootConfig;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Log4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    @DisplayName("BoardServce의 목록 불러오기")
    void getList() {
        for(BoardDTO board : service.getList()) {
            log.info(board);
        }
    }

    @Test
    @DisplayName("BoardService의 게시글 읽기")
    void get() {
        log.info(service.get(1L));
    }

    @Test
    @DisplayName("BoardService의 새 글 추가")
    void create() {
        BoardDTO board = new BoardDTO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 글");
        board.setWriter("serviceTEST user1");

        service.create(board);
        log.info("새로 생성된 게시물의 번호 : " + board.getNo());
    }

    @Test
    @DisplayName("BoardService의 글 수정")
    void update() {

        BoardDTO board = service.get(1L);
        board.setWriter("MEMBER");
        log.info("update RESULT: " + service.update(board));
    }

    @Test
    @DisplayName("BoardService의 글 삭제")
    void delete(){
        log.info("delete RESULT : " + service.delete(1L));
    }
}