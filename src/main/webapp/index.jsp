<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<!doctype html>
<head lang="en">
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title" bundle="${content}"/></title>
    <link href="${pageContext.request.contextPath}/css/style_index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/authorization.js" language="JavaScript"></script>
    <script type="text/javascript" src="js/jquery.min.js" language="JavaScript"></script>
    <script>
        $(document).ready(function(){
            $('#bttnLogin').click(function(){
                var login = $('#login').val();
                $.ajax({
                    type:'POST',
                    url:'Login',
                    data: {login: login},
                    success: function(){
                    }
                })
            });
        });
    </script>
</head>
<body>
<noscript>
    <h4><p>Включите JavaScript в вашем браузере, чтобы работать с нашим приложением</p></h4>

    <style type="text/css">
        .main {
            display: none;
        }
        p {
            text-align: center;
        }
    </style>
</noscript>
<div class="main">
    <div class="content">
        <p class="title"><span class="text"><img src="images/lib.png" width="76" height="77" hspace="10" vspace="10"
                                                 align="middle"></span></p>
        <p class="title"><fmt:message key="index.title" bundle="${content}"/></p>
        <p>&nbsp;</p>
    </div>
    <div class="login_div">
        <c:set var="errorMessage" scope="page"><fmt:message key="input.info" bundle="${content}"/></c:set>
        <form id="login_form" name="loginForm" action="login"
              onsubmit="return validateForm(this, '${errorMessage}');" method="POST">
            <fmt:message key="input.name" bundle="${content}"/> :
            <input type="text" value="" id="login" name="login" size="20"/>
            <input type="submit" id="login_button" value="<fmt:message key="entry" bundle="${content}"/>"/>
            <p id="error"></p>
        </form>
        <jsp:include page="WEB-INF/jsp/common/locales.jsp"/>
    </div>
    <div class="footer">
        <fmt:message key="author.info" bundle="${content}"/>
    </div>
</div>
</body>
</html>
