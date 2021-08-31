<%@ page import="entities.Discipline" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Дисциплины</title>
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
<h1>Управление дисциплиной</h1>
<h2>Имеющиеся дисциплины</h2>
<table>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Название дисциплины</th>
        <th scope="col">Дата старта проведения</th>
        <th scope="col">Количество занятий</th>
        <th scope="col">Цена</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <%
        List<Discipline> discs = (List<Discipline>) request.getAttribute("disc");
        for (Discipline dis : discs) {
    %>
    <form action="discipline" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="<%=dis.getIdDiscipline()%>"
                       title="<%=Integer.toString(dis.getIdDiscipline())%>" readonly></td>
            <td><input type="text" name="name" value="<%=dis.getTypeDiscipline()%>"
                       title="<%=dis.getTypeDiscipline()%>"></td>
            <td><input type="datetime-local" name="dateStart"
                       value="<%=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(dis.getDateStart())%>"
                       title="<%=dis.getDateStart()%>"></td>
            <td><input type="number" name="numberDis" style="width: 8em" value="<%=dis.getNumberDiscipline()%>"
                       title="<%=dis.getNumberDiscipline()%>" min="1" max="999"></td>
            <td><input type="number" name="price" style="width: 6em" value="<%=dis.getPrice()%>"
                       title="<%=dis.getPrice()%>" min="0" max="1000000"></td>
            <td>
                <button type="submit" name="action" value="edit" class="shine-button"><i>Обновить</i></button>
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
<h2>Добавить новую дисциплину</h2>
<form id="add-disc" action="discipline" method="POST">
    <table>
        <tr>
            <td><input type="text" name="name" placeholder="Введите название" title="Введите название"></td>
            <td><input type="datetime-local" name="dateStart" placeholder="Введитe дату старта"
                       title="Введите дату старта"></td>
            <td><input type="number" name="numberDis" style="width: 15em" placeholder="Введитe количество занятий"
                       title="Введите количество занятий" min="1" max="999"></td>
            <td><input type="number" name="price" placeholder="Введитe цену" title="Введите цену" min="0" max="1000000"></td>
            <td>
                <button type="submit" name="action" value="add" class="shine-button"><i>Добавить</i>
                </button>
            </td>
    </table>
</form>
<form id="request" action="discipline" method="post">
    <h2>Параметризованный запрос</h2>
    <p>Вывести название, количество занятий и цену дисциплины, цена которой находится в указанном диапазоне</p>
    <input type="number" name="pr1" max="100000" min="0" style="width: 16em" placeholder="Введите минимальную цену"
           title="Введите минимальную цену">
    <input type="number" name="pr2" max="100000" min="0" style="width: 16em" placeholder="Введите максимальную цену"
           title="Введите максимальную цену">
    <button type="submit" name="action" value="request" class="shine-button">
        <i>execute</i>
    </button>
</form>
<%
    if (request.getAttribute("check").equals("true")) {
%>
<table>
    <tr>
        <th scope="col">Вид дисциплины</th>
        <th scope="col">Количество занятий</th>
        <th scope="col">Цена</th>
    </tr>
    <% List<Discipline> disByPr = (List<Discipline>) request.getAttribute("prices");
        for (Discipline dis : disByPr) {
    %>
    <tr>
        <td><input type="text" name="name" value="<%=dis.getTypeDiscipline()%>"
                   title="<%=dis.getTypeDiscipline()%>" readonly></td>
        <td><input type="number" name="number" style="width: 6em" value="<%=dis.getNumberDiscipline()%>"
                   title="<%=dis.getNumberDiscipline()%>" readonly></td>
        <td><input type="text" name="type" value="<%=dis.getPrice()%>"
                   title="<%=dis.getPrice()%>" readonly></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
