<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="entities.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тренировки</title>
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
<h1>Управление тренировками</h1>
<h2>Проводимые тренировки</h2>
<table>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Дата проведения</th>
        <th scope="col">Тренер</th>
        <th scope="col">Клиент</th>
        <th scope="col">Дисциплина</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <%
        List<Training> trains = (List<Training>) request.getAttribute("trainings");
        for (Training training : trains) {
    %>
    <form action="training" method="POST">
        <tr>
            <td><input type="text" name="id" size="3" value="<%=training.getIdTraining()%>"
                       title="<%=Integer.toString(training.getIdTraining())%>" readonly></td>
            <td><input type="datetime-local" name="dateTr"
                       value="<%=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(training.getDateTraining())%>"
                       title="<%=training.getDateTraining()%>"></td>
            <td>
                <select name="employee">
                    <%
                        List<Employee> employees = (List<Employee>) request.getAttribute("emps");
                        for (Employee employee : employees) {
                            if (employee.getIdEmployee() == training.getFkEmp().getIdEmployee()) {
                    %>
                    <option value="<%=training.getFkEmp().getIdEmployee()%>"
                            title="<%=training.getFkEmp().getIdEmployee()%>" selected>
                        <%=employee.getFioEmployee()%>
                    </option>
                    <%} else {%>
                    <option value="<%=employee.getIdEmployee()%>" title="<%=employee.getIdEmployee()%>">
                        <%=employee.getFioEmployee()%>
                    </option>
                    <%
                            }
                        }%>
                </select>
            </td>
            <td>
                <select name="client">
                    <%
                        List<Client> clients = (List<Client>) request.getAttribute("clients");
                        for (Client client : clients) {
                            if (client.getIdClient() == training.getFkClient().getIdClient()) {
                    %>
                    <option value="<%=training.getFkClient().getIdClient()%>"
                            title="<%=training.getFkClient().getIdClient()%>" selected>
                        <%=client.getFioClient()%>
                    </option>
                    <%} else {%>
                    <option value="<%=client.getIdClient()%>" title="<%=client.getIdClient()%>">
                        <%=client.getFioClient()%>
                    </option>
                    <%
                            }
                        }%>
                </select>
            </td>
            <td>
                <select name="discipline">
                    <%
                        List<Discipline> disciplines = (List<Discipline>) request.getAttribute("disc");
                        for (Discipline dis : disciplines) {
                            if (dis.getIdDiscipline() == training.getFkDis().getIdDiscipline()) {
                    %>
                    <option value="<%=training.getFkDis().getIdDiscipline()%>"
                            title="<%=dis.getTypeDiscipline() +" "+dis.getDateStart()+" "+dis.getPrice()%>" selected>
                        <%=dis.getTypeDiscipline()%>
                    </option>
                    <%} else {%>
                    <option value="<%=dis.getIdDiscipline()%>"
                            title="<%=dis.getTypeDiscipline() +" "+dis.getDateStart()+" "+dis.getPrice()%>">
                        <%=dis.getTypeDiscipline()%>
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
<h2>Добавить новую тренировку</h2>
<form id="add-training" action="training" method="POST">
    <table>
        <tr>
            <td><select type="text" name="employee" placeholder="Выберите сотрудника"
                        title="Выберите сотрудника">
                <%
                    List<Employee> emps = (List<Employee>) request.getAttribute("emps");
                    for (Employee employee : emps) {
                %>
                <option value="<%=Integer.toString(employee.getIdEmployee())%>">
                    <%=employee.getFioEmployee()%>
                </option>
                <%
                    }
                %>
            </select></td>
            <td><select type="text"name="client" placeholder="Выберите клиента"
                        title="Выберите клиента">
                <%
                    List<Client> clients = (List<Client>) request.getAttribute("clients");
                    for (Client client : clients) {
                %>
                <option value="<%=Integer.toString(client.getIdClient())%>">
                    <%=client.getFioClient()%>
                </option>
                <%
                    }
                %>
            </select></td>
            <td><select type="text" name="discipline" placeholder="Выберите дисциплину"
                        title="Выберите дисциплину">
                <%
                    List<Discipline> disciplines = (List<Discipline>) request.getAttribute("disc");
                    for (Discipline discipline : disciplines) {
                %>
                <option value="<%=Integer.toString(discipline.getIdDiscipline())%>"
                        title="<%=discipline.getTypeDiscipline()+" "+discipline.getDateStart()+" "+discipline.getPrice()%>">
                    <%=discipline.getTypeDiscipline()%>
                </option>
                <%
                    }
                %>
            </select></td>
            <td><input type="datetime-local" name="dateTr" placeholder="Введитe дату" title="Введите дату"></td>
            <td>
                <button type="submit" name="action" value="add" class="shine-button"><i>Добавить</i>
                </button>
            </td>
        </tr>
    </table>
    <form id="request" action="training" method="post">
        <h2>Параметризованный запрос</h2>
        <p>Вывести название дисциплины, которую проводят, дату тренировки и полное ФИО тренера, по частично
            введенной фамилии</p>
        <input type="text" name="fioEmp" placeholder="Введите ФИО сотрудника"
               title="Введите ФИО сотрудника">
        <button type="submit" name="action" value="request" class="shine-button">
            <i>execute</i>
        </button>
    </form>
    <%
        if (request.getAttribute("check").equals("true")) {
    %>
    <table>
        <tr>
            <th scope="col">Название дисциплины</th>
            <th scope="col">Дата тренировки</th>
            <th scope="col">ФИО тренера</th>
        </tr>
        <%
            List<TrainingBean> trByEmp = (List<TrainingBean>) request.getAttribute("trByEmp");
            for (TrainingBean tr : trByEmp) {
        %>
        <tr>
            <td><input type="text" name="name" value="<%=tr.getNameDis()%>"
                       title="<%=tr.getNameDis()%>" readonly></td>
            <td><input type="datetime-local" name="date"
                       value="<%=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(tr.getDateTraining())%>"
                       title="<%=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(tr.getDateTraining())%>" readonly>
            </td>
            <td><input type="text" name="fio" value="<%=tr.getNameEmp()%>"
                       title="<%=tr.getNameEmp()%>" readonly></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</form>
</body>
</html>
