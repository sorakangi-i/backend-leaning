package org.study.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice // 이건 Controller에서 발생하는 예외를 여기에 처리해라.
@Log4j
public class CommonExceptionAdvice { // 이 클래스도 Controller이다. Exception도 컨트롤러이기 때문에. bean으로 등록이 필요하겠죠. 컴포넌트 스캔을 할수있도록 추가해라.
    @ExceptionHandler(Exception.class) // (여기에 경로 대신에 Catch할 예외를 먼저 제시한다. 모든 예외는 여기서 처리한다. Exception 최상위 클래스는 여기니까.)
    public String exception(Exception ex, Model model) { // 예외 객체와 model도 처리해라.
        log.error("Exception......." + ex.getMessage());
        model.addAttribute("exception", ex);
        log.error(model);

        return "error_page"; // 이것도 spring controller이니까 return은 view의 이름을 return한다. 즉, /error_page.jsp가 되겠죠. 500번 에러가 발생하면 여기로.
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // .NOT_FOUND는 404에 대한 상수이다.
    public String handle404(NoHandlerFoundException ex) {
        return "custom404";
    }
}
