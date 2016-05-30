<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="pagination">
    <c:if test="${booksCount gt 3}">
        <c:if test="${startPage eq 0}">
            <span class="disabled"> << </span>
        </c:if>
        <c:if test="${startPage gt 0}">
            <c:if test="${genreID ne null}">
                <a href="main?genreID=${genreID}&page=${page-1}"> << </a>
            </c:if>
            <c:if test="${genreID eq null}">
                <a href="search?search_string=${search_string}&search_option=${search_option}&page=${page-1}"> << </a>
            </c:if>
        </c:if>
        <span class="current">${page}</span>
        <c:if test="${startPage eq lastPage}">
            <span class="disabled"> >> </span>
        </c:if>
        <c:if test="${startPage lt lastPage}">
            <c:if test="${genreID ne null}">
                <a href="main?genreID=${genreID}&page=${page+1}"> >> </a>
            </c:if>
            <c:if test="${genreID eq null}">
                <a href="search?search_string=${search_string}&search_option=${search_option}&page=${page+1}"> >> </a>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${booksCount le 3 and booksCount gt 0}">
        <span class="current">1</span>
    </c:if>
</div>
