package org.study.board.controller;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.study.board.service.BoardService;
import org.study.config.RootConfig;
import org.study.config.ServletConfig;

import static org.junit.jupiter.api.Assertions.*;

@Log4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        RootConfig.class,
        ServletConfig.class
})
class BoardControllerTest {
    @Autowired
    BoardService service;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    @DisplayName("MockMvc를 통해 /list 조회 테스트")
    void list() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }

    @Test
    @DisplayName("MockMvc를 통해 Post create테스트")
    public void create() throws Exception {
        String resultPage = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/create")
                    .param("title", "테스트 새글 제목")
                    .param("content", "테스트 새글 내용")
                    .param("writer", "user1")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }

    @Test
    @DisplayName("MockMvc를 통한 게시글 1개 조회")
    public void get() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("no", "1"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }

    @Test
    @DisplayName("MockMvc를 통한 게시글 수정")
    public void update() throws Exception {
        String resultPage = mockMvc.perform(
                    MockMvcRequestBuilders.post("/board/update")
                            .param("no", "1")
                            .param("title", "수정된 테스트 제목")
                            .param("content", "수정된 글 내용")
                            .param("writer", "MockUser")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }

    @Test
    @DisplayName("MockMvc를 통한 게시글 삭제")
    public void delete() throws Exception {
        String resultPage = mockMvc.perform(
                    MockMvcRequestBuilders.post("/board/list")
                            .param("no", "3")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();
        log.info(resultPage);
    }
}