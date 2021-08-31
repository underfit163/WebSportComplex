<%@ page import="entities.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Sportcomplex" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сотрудники</title>
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
<h1>Управление сотрудниками</h1>
<h2>Работа с существующими записями</h2>
<table>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Имя сотрудника</th>
        <th scope="col">Телефон</th>
        <th scope="col">Дата рождения</th>
        <th scope="col">Образование</th>
        <th scope="col">Зарплата</th>
        <th scope="col">Должность</th>
        <th scope="col">Опыт</th>
        <th scope="col">Пол</th>
        <th scope="col">Спорткомплекс</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("emp");
        for (Employee emp : employees) {
    %>
    <form action="employee" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="<%=emp.getIdEmployee()%>"
                       title="<%=emp.getIdEmployee()%>" readonly></td>
            <td><input type="text" name="name" value="<%=emp.getFioEmployee()%>"
                       title="<%=emp.getFioEmployee()%>"></td>
            <td><input type="number" name="phone" style="width: 11em" value="<%=emp.getPhoneEmployee()%>"
                       title="<%=emp.getPhoneEmployee()%>" min="10000"></td>
            <td><input type="date" name="birthday" value="<%=emp.getBirthday()%>"
                       title="<%=emp.getBirthday()%>"></td>
            <td><input type="text" name="education" size="10" value="<%=emp.getEducation()%>"
                       title="<%=emp.getEducation()%>"></td>
            <td><input type="number" name="salary" style="width: 6em" value="<%=emp.getSalary()%>"
                       title="<%=emp.getSalary()%>" min="10000"></td>
            <td><input type="text" name="position" size="10" value="<%=emp.getPositionEmp()%>"
                       title="<%=emp.getPositionEmp()%>"></td>
            <td><input type="number" name="exp" style="width: 3em" value="<%=emp.getWorkExp()%>"
                       title="<%=emp.getWorkExp()%>" min="0" max="99"></td>
            <td><select name="gender">
                <% String gender1;
                    String gender2;
                    if (emp.getGender().equals("male")) {
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
            </select>
            </td>
            <td>
                <select name="sportcomplex">
                    <%
                        List<Sportcomplex> sportcomplexes = (List<Sportcomplex>) request.getAttribute("scs");
                        for (Sportcomplex sc : sportcomplexes) {
                            if (sc.getIdSc() == emp.getFkSc().getIdSc()) {
                    %>
                    <option value="<%=emp.getFkSc().getIdSc()%>" title="<%=emp.getFkSc().getIdSc()%>" selected>
                        <%=sc.getNameSc()%>
                    </option>
                    <%} else {%>
                    <option value="<%=sc.getIdSc()%>" title="<%=sc.getIdSc()%>">
                        <%=sc.getNameSc()%>
                    </option>
                    <%
                            }
                        }%>
                </select>
            </td>
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
<h2>Добавить нового сотрудника</h2>
<form id="add-emp" action="employee" method="POST">
    <table>
        <tr>
            <td><select type="text" name="sportcomplex" placeholder="Введите спорткомплекс"
                        title="Введите спорткомплекс">
                <%
                    List<Sportcomplex> sportcomplexes = (List<Sportcomplex>) request.getAttribute("scs");
                    for (Sportcomplex sc : sportcomplexes) {
                %>
                <option value="<%=Integer.toString(sc.getIdSc())%>">
                    <%=sc.getNameSc()%>
                </option>
                <%
                    }
                %>
            </select></td>
            <td><input type="text" name="name" size="15" placeholder="Введите ФИО" title="Введите ФИО"></td>
            <td><input type="number" name="phone" style="width: 12em" placeholder="Введите телефон"
                       title="Введите телефон"></td>
            <td><input type="date" name="birthday" placeholder="Введите дату день рожденья" title="Введите дату"></td>
            <td><input type="text" name="education" size="15" placeholder="Введите образование"
                       title="Введите образование"></td>
            <td><input type="number" name="salary" style="width: 10em" placeholder="Введите зарплату"
                       title="Введите зарплату"></td>
            <td><input type="text" name="position" size="15" placeholder="Введите должность" title="Введите должность">
            </td>
            <td><input type="number" name="exp" style="width: 8em" placeholder="Введите опыт" title="Введите опыт"></td>
            <td><select type="text" name="gender" placeholder="Введите пол"
                        title="Введите пол">
                <option value="male" title="male">male
                </option>
                <option value="female" title="female">female
                </option>
            </select>
            <td>
                <button type="submit" name="action" value="add" class="shine-button"><i>Добавить</i>
                </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
