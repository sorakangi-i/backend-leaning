<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>

<h2 class="page-header my-4">글 수정</h2>
<div>
    <form role="form" method="post">
        <input type="hidden" name="no" value="${board.no}">
        <div>
            <label>제목</label>
            <input name="title" class="form-control" value="${board.title}">
        </div>

        <div>
            <label>작성자</label>
            <input name="writer" class="form-control" value="${board.writer}">
        </div>

        <div>
            <label>내용</label>
            <textarea class="form-control" name="content" row="10">${board.content}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">확인</button>
        <button type="reset" class="btn btn-primary">취소</button>
        <a href="get?no=${board.no}" class="btn btn-primary">돌아가기</a>
        <a href="list" class="btn btn-primary">목록</a>
    </form>
</div>

<%@include file="../layouts/footer.jsp"%>
</html>