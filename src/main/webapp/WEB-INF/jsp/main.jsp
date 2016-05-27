<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Онлайн библиотека::Главная</title>
    <link href="${pageContext.request.contextPath}/css/style_main.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:useBean id="book" class="entity.impl.Book" scope="request"/>
<jsp:useBean id="genre" class="entity.impl.Genre" scope="request"/>
<div class="container">
    <jsp:include page="common/header.jsp"/>
    <jsp:include page="common/left-menu.jsp"/>
    <div class="context">gkgyvyvkuvyyvkuyvuvuvuyvouyvuyvkuvyyuv</div>
    <div class="enter"></div>
    <div class="footer"></div>
</div>
</body>
</html>
