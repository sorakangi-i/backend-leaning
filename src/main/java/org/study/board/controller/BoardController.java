package org.study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.board.domain.BoardAttachmentVO;
import org.study.board.dto.BoardDTO;
import org.study.board.service.BoardService;
import org.study.common.util.UploadFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@Log4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/create")
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);

        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/update"})
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board, RedirectAttributes ra) {
        log.info("update: " + board);
        if(service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {
        log.info("delete......" + no);
        service.delete(no);

        return "redirect:/board/list";
    }

    // 다운로드
    @GetMapping("/download/{no}")
    @ResponseBody // view를 사용하지 않고 직접 내보냄
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {
        BoardAttachmentVO attach = service.getAttachment(no);
        File file = new File(attach.getPath());

        UploadFiles.download(response, file, attach.getFilename());
    }
}
