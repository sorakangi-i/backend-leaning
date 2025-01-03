<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>

<h2 class="page-header my-4">글 목록 보기</h2>
<div class="text-right">
    <a href="create" class="btn btn-primary">
        글쓰기
    </a>
</div>

<table class="table table-hover">
    <thead>
    <tr>
        <th style="width:60px">No</th>
        <th>제목</th>
        <th style="width:100px">작성자</th>
        <th style="width:130px">등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.no}</td>
            <td><a href="get?no=${board.no}">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-right">
    <a href="create" class="btn btn-primary">
        글쓰기
    </a>
</div>

<%@include file="../layouts/footer.jsp"%>
</html>