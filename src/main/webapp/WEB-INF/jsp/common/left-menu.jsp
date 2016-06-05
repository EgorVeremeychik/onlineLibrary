<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<div class="left-menu">
    <ul>
        <li>
            <c:if test="${genre.id == alreadySaw}">
                <a style="color: red" href="main?genreID=0&page=1">Все книги</a>
            </c:if>
            <c:if test="${genre.id != alreadySaw}">
                <a href="main?genreID=0&page=1">Все книги</a>
            </c:if>
        </li>
        <c:forEach var="genre" items="${genreList}">
            <li>
                <c:if test="${genre.id == alreadySaw}">
                    <a style="color: red" href="main?genreID=${genre.id}&page=1">${genre.name}</a>
                </c:if>
                <c:if test="${genre.id != alreadySaw}">
                    <a href="main?genreID=${genre.id}&page=1">${genre.name}</a>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>
