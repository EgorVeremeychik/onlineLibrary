<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 400</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h3 style="color:red;">Error 400</h3>
    <h3>Возможно ошибки в параметрах сервлета:</h3>
    <h3>Имена параметров должны быть one, two, operation</h3>
    <h3>Operation должен принимать 1 из 4 значений: add, subtract, multiply, divide</h3>
    <h3>Значения one и two должны быть числами</h3>
    <h1>Пример</h1>
    <h3>/CalcServlet?one=1&two=3&operation=add</h3>
</body>
</html>
