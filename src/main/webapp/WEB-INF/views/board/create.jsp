<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>

<h2>새 글쓰기</h2>
<div>
    <form method="post">
        <div>
            <label>제목 : </label>
            <input name="title" class="form-contol">
        </div>
        <div>
            <label>작성자 : </label>
            <input name="writer" class="form-control">
        </div>
        <div>
            <label>내용 : </label>
            <textarea class="form-control" name="content" rows="15"></textarea>
        </div>

        <div class="mt-2">
            <button type="submit" class="btn btn-primary">확인</button>
            <button type="reset" class="btn btn-primary">취소</button>
            <a href="list" clas="btn btn-primary">목록</a>
        </div>
    </form>
</div>


<%@include file="../layouts/footer.jsp"%>
</html>