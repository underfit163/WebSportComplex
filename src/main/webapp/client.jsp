<%@ page import="entities.Client" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Клиенты</title>
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
<h1>Управление клиентами</h1>
<h2>Имеющиеся клиенты</h2>
<table>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя клиента</th>
        <th scope="col">Дата рождения</th>
        <th scope="col">Пол</th>
        <th scope="col">Телефон</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <%
        List<Client> clientList = (List<Client>) request.getAttribute("clients");
        for (Client client : clientList) {
    %>
    <form action="client" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="<%=client.getIdClient()%>"
                       title="<%=Integer.toString(client.getIdClient())%>" readonly></td>
            <td><input type="text" name="name" value="<%=client.getFioClient()%>"
                       title="<%=client.getFioClient()%>"></td>
            <td><input type="date" name="birthday" value="<%=client.getBirthday()%>"
                       title="<%=client.getBirthday()%>"></td>
            <td><select name="gender">
                <% String gender1;
                    String gender2;
                    if (client.getGender().equals("male")) {
                        gender1 = "male";
                        gender2 = "female";
                    } else {
                        gender1 = "female";
                        gender2 = "male";
                    }
                %>
                <option value="<%=gender1%>" title="gender1"><%=gender1%>
                </option>
                <option value="<%=gender2%>" title="gender2"><%=gender2%>
                </option>
            </select></td>
            <td><input type="number" name="phone" style="width: 11em" value="<%=client.getPhoneClient()%>"
                       title="<%=client.getPhoneClient()%>" min="10000" max="99999999999"></td>
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
<h2>Добавить нового клиента</h2>
<form id="add-client" action="client" method="POST">
    <table>
        <tr>
            <td><input type="text" name="name" placeholder="Введите ФИО" title="Введите ФИО"></td>
            <td><input type="date" name="birthday" placeholder="Введитe дату рождения" title="Введите дату рождения"></td>
            <td><select type="text" name="gender" placeholder="Введите пол" title="Введите пол">
                <option value="male" title="male">male
                </option>
                <option value="female" title="female">female
                </option>
            </select>
            <td><input type="number" name="phone" style="width: 15em" placeholder="Введите телефон" title="Введите телефон" min="10000"></td>
            <td>
                <button type="submit" name="action" value="add" class="shine-button"><i>Добавить</i>
                </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
