package servlets;

import dao.ClientDao;
import dao.DisciplineDao;
import dao.EmployeeDao;
import dao.TrainingDao;
import entities.Training;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/training")
public class TrainingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrainingDao trainingDao = new TrainingDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ClientDao clientDao = new ClientDao();
        DisciplineDao disciplineDao = new DisciplineDao();
        req.setAttribute("trainings", trainingDao.selectTraining());
        req.setAttribute("disc", disciplineDao.selectDiscipline());
        req.setAttribute("emps", employeeDao.selectEmployees());
        req.setAttribute("clients", clientDao.selectClients());
        req.setAttribute("check", "false");
        req.getRequestDispatcher("training.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        TrainingDao trainingDao = new TrainingDao();
        EmployeeDao employeeDao = new EmployeeDao();
        ClientDao clientDao = new ClientDao();
        DisciplineDao disciplineDao = new DisciplineDao();
        switch (action) {
            case "add": {
                try {
                    trainingDao.insertTraining(
                            Integer.parseInt(req.getParameter("discipline")),
                            Integer.parseInt(req.getParameter("employee")),
                            Integer.parseInt(req.getParameter("client")),
                            new Training(new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("dateTr")).getTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                resp.sendRedirect(req.getContextPath() + "/training");
                break;
            }
            case "edit": {
                Training training = trainingDao.findById(Integer.parseInt(req.getParameter("id")));
                try {
                    training.setDateTraining(new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("dateTr")).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                training.setFkEmp(employeeDao.findById(Integer.parseInt(req.getParameter("employee"))));
                training.setFkClient(clientDao.findById(Integer.parseInt(req.getParameter("client"))));
                training.setFkDis(disciplineDao.findById(Integer.parseInt(req.getParameter("discipline"))));
                trainingDao.updateTraining(training);
                resp.sendRedirect(req.getContextPath() + "/training");
                break;
            }
            case "delete": {
                trainingDao.deleteTraining(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/training");
                break;
            }
            case "request": {
                req.setAttribute("trainings", trainingDao.selectTraining());
                req.setAttribute("disc", disciplineDao.selectDiscipline());
                req.setAttribute("emps", employeeDao.selectEmployees());
                req.setAttribute("clients", clientDao.selectClients());
                req.setAttribute("check", "true");
                req.setAttribute("trByEmp", trainingDao.selectTrByEmp(req.getParameter("fioEmp")));
                req.getRequestDispatcher("training.jsp").forward(req, resp);
                break;
            }
        }
    }
}
