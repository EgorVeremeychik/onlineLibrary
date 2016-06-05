<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang ne null ? lang : pageContext.request.locale}"/>
<fmt:setBundle basename="properties.content" var="content"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title" bundle="${content}"/></title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/modal.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"
            language="JavaScript"></script>
</head>
<body>
<jsp:useBean id="genre" class="entity.impl.Genre" scope="request"/>
<jsp:include page="common/header.jsp"/>
<div id="wrap">
    <div class="logo">
        <c:if test="${ lang eq 'ru_RU' or lang eq null}">
            <a href="#"><img src="images/ru/logo.png" width="211" height="46" alt=""/></a>
        </c:if>
        <c:if test="${lang eq 'en_EN'}">
            <a href="#"><img src="images/en/logo.png" width="211" height="46" alt=""/></a>
        </c:if>
    </div>
    <div class="menu">
        <ul>
            <li><a href="#">Новинки</a></li>
            <li><a href="#">Популярные</a></li>
            <li><a href="#">О компании</a></li>
            <li><a href="#">Контакты</a></li>
            <li><a href="#">Команда</a></li>
        </ul>
    </div>
    <a class="feedback" href="#">Обратная связь</a>
    <div class="clear"></div>
    <div class="search">
        <c:set var="searchBooks" scope="page"><fmt:message key="search.books" bundle="${content}"/></c:set>
        <form action="search" name="search_form" method="get">
            <input type="text" class="search-text" placeholder="${searchBooks}" name="search_string" value="" size="90"
                   required maxlength="50"/>
            <input type="submit" class="button-text"/>
            <input type="submit" class="button" value="<fmt:message key="search" bundle="${content}"/>"/>
            <div class="dropdown">
                <select name="search_option">
                    <option value="По названию" ${search_option == "По названию" ? 'selected="selected"' : ''}>
                        <fmt:message
                                key="book.name" bundle="${content}"/></option>
                    <option value="По автору" ${search_option == "По автору" ? 'selected="selected"' : ''}><fmt:message
                            key="author.name" bundle="${content}"/></option>
                </select>
            </div>
            <input type="hidden" name="page" value="1">
        </form>
    </div>
    <div class="clear"></div>
    <div class="nav">
        <ul>
            <li><a href="#">А</a></li>
            <li><a href="#">Б</a></li>
            <li><a href="#">В</a></li>
            <li><a href="#">Г</a></li>
            <li><a href="#">Д</a></li>
            <li><a href="#">Е</a></li>
            <li><a href="#">Ё</a></li>
            <li><a href="#">Ж</a></li>
            <li><a href="#">З</a></li>
            <li><a href="#">И</a></li>
            <li><a href="#">Й</a></li>
            <li><a href="#">К</a></li>
            <li><a href="#">Л</a></li>
            <li><a href="#">М</a></li>
            <li><a href="#">Н</a></li>
            <li><a href="#">О</a></li>
            <li><a href="#">П</a></li>
            <li><a href="#">Р</a></li>
            <li><a href="#">С</a></li>
            <li><a href="#">Т</a></li>
            <li><a href="#">У</a></li>
            <li><a href="#">Ф</a></li>
            <li><a href="#">Х</a></li>
            <li><a href="#">Ц</a></li>
            <li><a href="#">Ч</a></li>
            <li><a href="#">Ш</a></li>
            <li><a href="#">Щ</a></li>
            <li><a href="#">Ъ</a></li>
            <li><a href="#">Ы</a></li>
            <li><a href="#">Ь</a></li>
            <li><a href="#">Э</a></li>
            <li><a href="#">Ю</a></li>
            <li><a href="#">Я</a></li>
        </ul>
    </div>
    <!-- content -->
    <div class="content">
        <jsp:include page="common/left-menu.jsp"/>
        <div class="right-content">
            <div class="box-sh">
                <h1><fmt:message key="book.count" bundle="${content}"/> : ${booksCount}</h1>
                <a href="#addBook"><p><fmt:message key="book.add" bundle="${content}"/></p></a>
            </div>
            <c:forEach var="book" items="${books}">
                <div class="item">
                    <img src="images/books/${book.image}" width="100" height="147" alt=""/>
                    <div class="info">
                        <div class="book_name">
                            <a class="title" href="#">${book.name}</a>
                        </div>
                        <span>${book.author.name}</span>
                        <p>${book.pageCount} <fmt:message key="page.count" bundle="${content}"/></p>
                        <p><fmt:message key="publisher" bundle="${content}"/>: <a href="#">${book.publisher.name}</a>
                        </p>
                        <p><fmt:message key="publish.year" bundle="${content}"/>: ${book.publishDate} <fmt:message
                                key="year" bundle="${content}"/></p>
                        <p><fmt:message key="isbn" bundle="${content}"/>:${book.isbn}</p>
                    </div>
                    <div class="clear"></div>
                    <a class="mr" href="#"><p class="read"><fmt:message key="read" bundle="${content}"/></p></a>
                    <a class="mr" href="#"><p class="download"><fmt:message key="download" bundle="${content}"/></p></a>
                    <a class="mr" href="javascript:;"><p class="edit" onclick="bookMore(${book.id})">
                        <fmt:message key="book.edit" bundle="${content}"/></p></a>
                    <a class="delete-button" href="#"><p class="delete">
                        <fmt:message key="book.delete" bundle="${content}"/></p></a>
                </div>
            </c:forEach>
            <div class="clear"></div>
            <jsp:include page="common/pagination.jsp"/>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer_blank"></div>
</div>
<div id="footer">
    <div class="footer-wrap">
        <p>&#171;<fmt:message key="author.info" bundle="${content}"/>&#187;</p>
    </div>
</div>
<div id="addBook" class="modalDialog">
    <div class="popup-container">
        <h2>Редактирование книги</h2>
        <div class="popup-info">
            <div class="avatar-box">
                <img class="c-img" src="" height="149" width="110">
                <a href="#"><img src="images/modal/upload.png" height="149" width="110"></a>
            </div>
            <div class="ava-info">
                <div class="block">
                    <p>Название:</p>
                    <div class="ava-form">
                        <input type="text" class="c-name">
                    </div>
                </div>
                <div class="block">
                    <p>Жанр:</p>
                    <div class="ava-form">
                        <div class="dropdown1">
                            <select class="c-genre">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="block">
                    <p>Автор:</p>
                    <div class="ava-form">
                        <div class="dropdown1">
                            <select class="c-author">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="block">
                    <p>Издательство:</p>
                    <div class="ava-form">
                        <div class="dropdown1">
                            <select class="c-publisher">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="block">
                    <p>ISBN:</p>
                    <div class="ava-form">
                        <input type="text" class="nw c-isbn">
                    </div>
                </div>
                <div class="block">
                    <p>Год:</p>
                    <div class="ava-form">
                        <input type="text" class="nw c-year">
                    </div>
                </div>
                <div class="block">
                    <p>Страниц:</p>
                    <div class="ava-form">
                        <input type="text" class="nw c-pages">
                    </div>
                </div>
                <div class="block">
                    <p>Текст книги:</p>
                    <div class="ava-form">
                        <div class="loaded">
                            <a class="name" href="#">Bremm-muz...</a>
                            <a class="close" href="#"><img src="images/modal/close.png" height="36" width="41"></a>
                        </div>
                        <a class="load-pdf" href="#"><span>Загрузить PDF</span></a>
                    </div>
                </div>
                <div class="block">
                    <p>Описание: <span>200 символов осталось</span></p>
                    <div class="ava-form">
                        <textarea class="c-descr"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom-info">
            <p>Заполните все поля!</p>
            <div class="button-right">
                <a class="cancel" href="javascript:;" onclick="closeModal()">Отмена</a>
                <a class="save" href="#">Сохранить</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/authorization.js"
        language="JavaScript"></script>
</body>
</html>
