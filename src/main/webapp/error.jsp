<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Исключение</title>
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="StyleCSS/table.css">
</head>
<body>
<ul class="nav">
    <li><a href="main" class="s2">Главная страница</a></li>
    <li><a href="sportcomplex" class="s2">Спорткомплексы</a></li>
    <li><a href="employee" class="s2">Сотрудники</a></li>
    <li><a href="client" class="s2">Клиенты</a></li>
    <li><a href="discipline" class="s2">Дисциплины</a></li>
    <li><a href="training" class="s2">Тренировки</a></li>
</ul>
<h1>Страница обработки ошибок</h1>
<h2>Исключение произошло при обработке запроса</h2>
<p>Type: <%= exception%></p>
<p>Message: <%=message%></p>
</body>
</html>
