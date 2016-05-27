<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="pagination">
    <form action="main?genreID=${genreID}" method="post" name="nextPage">
        <input type="hidden" name="pageNo" value="${pageNo+1}">
        <input type="hidden" name="startPage" value="${pageNo+1}">

    </form>
    <form action="main?genreID=${genreID}" method="post" name="prevPage">
        <input type="hidden" name="pageNo" value="${pageNo-1}">
        <input type="hidden" name="startPage" value="${pageNo-1}">
        <input type="hidden" name="genreID" value="${genre.id}">
    </form>
    <c:if test="${booksCount gt 3}">
        <c:if test="${startPage eq 0}">
            <span class="disabled"> << </span>
        </c:if>
        <c:if test="${startPage gt 0}">
            <a href="javascript:;" onclick="document.prevPage.submit();"> << </a>
        </c:if>
        <span class="current">${pageNo}</span>

        <c:if test="${startPage eq lastPage}">
            <span class="disabled"> >> </span>
        </c:if>
        <c:if test="${startPage lt lastPage}">
            <a href="javascript:;" onclick="document.nextPage.submit();"> >> </a>
        </c:if>
    </c:if>
    <c:if test="${booksCount le 3 and booksCount gt 0}">
        <span class="current">1</span>
    </c:if>
</div>
