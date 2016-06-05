<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<div class="header">
    <div class="header-wrap">
        <jsp:include page="locales.jsp"/>

        <div class="social">
            <a href="#"><img src="images/vk.jpg" width="16" height="16" alt=""></a>
            <a href="#"><img src="images/facebook.jpg" width="16" height="16" alt=""></a>
        </div>
        <div class="aut">
            <p>${user.login}</p>
            <a href="logout"><fmt:message key="exit" bundle="${content}"/></a>
        </div>
    </div>
</div>
