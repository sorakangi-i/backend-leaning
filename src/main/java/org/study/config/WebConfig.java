package org.study.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 파일 업로드 처리를 위한 변수
    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;
    final long MAX_REQUEST_SIZE = 1024 * 1024 * 20L;
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class }; // rootConfig가 어디있느냐
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class }; // servletconfig가 어디있느냐
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url매핑 패턴, / : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // @WebServlet("")에게 전달될
    }

    // 문자 인코딩 필터. POST body 문자 인코딩 필터 설정 - UTF-8 설정
    protected Filter[] getServletFilters() { // return이 Filter[]의 배열이다.
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true); // 항상 해야한다.

        return new Filter[] {characterEncodingFilter};
    }

    // 파일 업로드 처리를 위한 메서드
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // 요청이 왔을 때, 그 요청을 처리할 컨트롤러가 없으면 예외처리를 던져라.

        // 파일 업로드 설정
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                LOCATION, // 업로드 처리 디렉토리 경로
                MAX_FILE_SIZE, // 요청하나당 업로드 가능한 파일 하나의 최대 크기
                MAX_REQUEST_SIZE, // 업로드 가능한 전체 최대 크기(여러 파일 업로드 하는 경우)
                FILE_SIZE_THRESHOLD // 메모리 파일의 최대 크기(이보다 작으면 실제 메모리에서만 작업)
        );
        registration.setMultipartConfig(multipartConfig);
    }
}
