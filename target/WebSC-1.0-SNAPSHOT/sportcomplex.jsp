<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.Sportcomplex" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Спорткомплексы</title>
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
<h1>Управление спорткомплексами</h1>
<h2>Имеющиеся сотрудники</h2>
<table>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Название спорткомплекса</th>
        <th scope="col">Телефон</th>
        <th scope="col">Адрес</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <%
        List<Sportcomplex> scs = (List<Sportcomplex>) request.getAttribute("scs");
        for (Sportcomplex sportcomplex : scs) {
    %>
    <form action="sportcomplex" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="<%=sportcomplex.getIdSc()%>"
                       title="<%=Integer.toString(sportcomplex.getIdSc())%>" readonly></td>
            <td><input type="text" name="name" value="<%=sportcomplex.getNameSc()%>"
                       title="<%=sportcomplex.getNameSc()%>"></td>
            <td><input type="number" name="phone" style="width: 11em" value="<%=sportcomplex.getPhoneSc()%>"
                       title="<%=sportcomplex.getPhoneSc().toString()%>" maxlength="11"></td>
            <td><input type="text" name="address" value="<%=sportcomplex.getAddressSc()%>"
                       title="<%=sportcomplex.getAddressSc()%>"></td>
            <td>
                <button type="submit" name="action" value="edit" class="shine-button"><i>Обновить</i>
                </button>
            </td>
            <td>
                <button type="submit" name="action" value="delete" class="shine-button"><i>Удалить</i></button>
            </td>
        </tr>
    </form>
    <%
        }
    %>
</table>
<h2>Добавить новый спорткомплекс</h2>
<form id="add-SC" action="sportcomplex" method="POST">
    <table>
        <tr>
            <td><input type="text" name="name" placeholder="Введите название" title="Введите название"></td>
            <td><input type="number" name="phone" size="14" placeholder="Введите телефон" title="Введите телефон"></td>
            <td><input type="text" name="address" placeholder="Введите адрес" title="Введите адрес"></td>
            <td><button type="submit" name="action" value="add" class="shine-button"><i>Добавить</i></button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
