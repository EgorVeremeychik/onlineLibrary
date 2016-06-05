<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title" bundle="${content}"/></title>
    <link href="${pageContext.request.contextPath}/css/authorization.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/authorization.js" language="JavaScript"></script>
    <script type="text/javascript" src="js/jquery.min.js" language="JavaScript"></script>
</head>
<body>
<noscript>
    <h2 style="margin-top:200px;"><p>Включите JavaScript в вашем браузере, чтобы работать с нашим приложением</p></h2>


    <style type="text/css">
        .authorization {
            display: none;
        }

        p {
            text-align: center;
        }
    </style>

</noscript>
<div class="authorization">
    <div class="lg">
        <c:if test="${ lang eq 'ru_RU' or lang eq null}">
            <a href="#"><img src="images/ru/logo.png" width="211" height="46" alt=""/></a>
        </c:if>
        <c:if test="${lang eq 'en_EN'}">
            <a href="#"><img src="images/en/logo.png" width="211" height="46" alt=""/></a>
        </c:if>
    </div>
    <jsp:include page="WEB-INF/jsp/common/locales.jsp"/>
    <div class="clear"></div>
    <c:set var="errorMessage" scope="page"><fmt:message key="input.info" bundle="${content}"/></c:set>
    <c:set var="name" scope="page"><fmt:message key="input.name" bundle="${content}"/></c:set>
    <c:set var="password" scope="page"><fmt:message key="input.password" bundle="${content}"/></c:set>
    <c:set var="entry" scope="page"><fmt:message key="entry" bundle="${content}"/></c:set>
    <form name="loginForm" action="login"
          onsubmit="return validateForm(this, '${errorMessage}');" method="POST">
        <input type="text" placeholder="${name}" class="aut-text" id="login" name="login"/>
        <input type="text" placeholder="${password}" class="aut-text" id="password" name="password"/>
        <div style="float:left; height: 15px; width: 100%; text-align: center; height:50px;">

        </div>
        <div class="div-login-button">
            <table class="panel-login">
                <tr>
                    <td class="col">
                        <input type="submit" class="login-button" value="${entry}"/>
                    </td>
                    <td class="col"></td>
                    <td class="col">
                        <div class="save-me">
                            <label><input type="checkbox"/><fmt:message key="remember.me" bundle="${content}"/></label>
                        </div>
                    </td>
                </tr>
            </table>


        </div>
    </form>
</div>
</body>
</html>
