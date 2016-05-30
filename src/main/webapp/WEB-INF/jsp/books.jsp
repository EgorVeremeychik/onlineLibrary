<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}" />
<fmt:setBundle basename="properties.content" var="content"/>
<!doctype html>
<head lang="en">
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title" bundle="${content}"/></title>
    <link href="${pageContext.request.contextPath}/css/style_main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" language="JavaScript"></script>
    <script type="text/javascript">
        $('.ajaxAction').click(function () {
            $(this).find('a').click();
            alert("ssdgsdfsd");
            return false;
        });
    </script>
</head>
<body>
<jsp:useBean id="genre" class="entity.impl.Genre" scope="request"/>
<div class="container">
    <jsp:include page="common/header.jsp"/>
    <jsp:include page="common/left-menu.jsp"/>
    <div class="book_list">
        <h4><fmt:message key="book.count" bundle="${content}"/> : ${booksCount}</h4>
        <jsp:include page="common/pagination.jsp"/>
        <c:forEach var="book" items="${books}">
            <div class="book_info">
                <div class="book_image">
                    <img src="images/books/${book.image}" alt="Book image" height="160" width="120">
                </div>
                <div class="book_details">
                    <p><a href="#">${book.name}</a></p>
                    <p><strong><fmt:message key="author.name" bundle="${content}"/> : </strong>${book.author.name}</p>
                    <p><strong><fmt:message key="page.count" bundle="${content}"/> : </strong>${book.pageCount}</p>
                    <p><strong><fmt:message key="publisher" bundle="${content}"/> : </strong>${book.publisher.name}</p>
                    <p><strong><fmt:message key="publish.year" bundle="${content}"/> :</strong>${book.publishDate}</p>
                    <p><strong><fmt:message key="isbn" bundle="${content}"/> : </strong>${book.isbn}</p>
                </div>
                <div class="book_desc">
                    <p><strong><fmt:message key="descr" bundle="${content}"/> :</strong></p>
                    <p>${book.description}</p>
                </div>
            </div>
        </c:forEach>
        <jsp:include page="common/pagination.jsp"/>
    </div>
    <div class="enter"></div>
    <div class="footer"></div>
</div>
</body>
</html>
