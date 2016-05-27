<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}" />
<fmt:setBundle basename="properties.content" var="content"/>
<div class="sidebar">
    <h4><fmt:message key="genres" bundle="${content}" /> :</h4>
    <ul class="nav">
        <li>
            <form class="formClass" action="main" method="get">
                <input type="hidden" name="genreID" value="0">
                <c:if test="${genre.id == alreadySaw}">
                    <a style="color: red" href="javascript:;" onclick="parentNode.submit();">Все книги</a>
                </c:if>
                <c:if test="${genre.id != alreadySaw}">
                    <a href="javascript:;" onclick="parentNode.submit();">Все книги</a>
                </c:if>
            </form>
        </li>
        <c:forEach var="genre" items="${genreList}">
            <li>
                <form class="formClass" action="main" method="get">
                    <input type="hidden" name="genreID" value="${genre.id}">
                    <c:if test="${genre.id == alreadySaw}">
                        <a style="color: red" href="javascript:;" onclick="parentNode.submit();">${genre.name}</a>
                    </c:if>
                    <c:if test="${genre.id != alreadySaw}">
                        <a href="javascript:;" onclick="parentNode.submit();">${genre.name}</a>
                    </c:if>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
