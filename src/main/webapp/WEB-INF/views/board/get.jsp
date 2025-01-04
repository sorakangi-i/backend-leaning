<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>

<h2 class="page-header my-4">${board.title}</h2>

<div class="d-flex justify-content-between">
    <div>${board.writer}</div>
    <div>
        <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
    </div>
</div>
<div class="text-end">
    <c:forEach var="file" items="${board.attaches}">
        <div class="attach-file-item">
            <a href="/board/download/${file.no}" class="file-link">
                <i class="fa-solid fa-floppy-disk"></i>
                ${file.filename} (${file.fileSize})
            </a>
        </div>
    </c:forEach>
</div>
<hr>
<div>
    <pre>${board.content}</pre>
</div>

<div class="mt-4 text-right">
    <a href="list" class="btn btn-primary">목록</a>
    <a href="update?no=${board.no}" class="btn btn-primary">수정</a>
    <a href="#" class="btn btn-primary delete">삭제</a>
</div>

<form action="delete" method="post" id="deleteForm">
    <input type="hidden" name="no" value="${board.no}" />
</form>

<script src="/resources/js/main.js"></script>

<%@include file="../layouts/footer.jsp"%>
</html>