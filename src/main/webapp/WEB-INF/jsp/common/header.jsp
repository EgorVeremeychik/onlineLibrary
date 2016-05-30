<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<div class="header">
    <div class="logo">
        <img src="images/library.png" alt="Logo" name="logo">
    </div>
    <div class="descr">
        <h3><fmt:message key="index.title" bundle="${content}"/></h3>
    </div>
    <div class="logout_form">
        <jsp:include page="locales.jsp"/>
        <h5><fmt:message key="welcome" bundle="${content}"/>, <c:out value="${user.login}"/></h5>
        <form action="logout" method="post">
            <a href="javascript:" onclick="parentNode.submit()"><fmt:message key="exit" bundle="${content}"/></a>
        </form>
    </div>
    <div class="search_form">
        <form action="search" name="search_form" method="get">
            <input type="text" name="search_string" value="" size="90" required maxlength="50">
            <input type="submit" class="search_button" value="<fmt:message key="search" bundle="${content}"/>">
            <select id="sofwol" name="search_option">
                <option value="Название" ${search_option == "Название" ? 'selected="selected"' : ''}><fmt:message
                        key="book.name" bundle="${content}"/></option>
                <option value="Автор" ${search_option == "Автор" ? 'selected="selected"' : ''}><fmt:message
                        key="author.name" bundle="${content}"/></option>
            </select>
            <input type="hidden" name="page" value="1">
        </form>
    </div>
    <div style="clear: both"></div>
</div>
