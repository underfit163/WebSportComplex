package servlets;

import dao.EmployeeDao;
import dao.SportcomplexDao;
import entities.Employee;
import entities.Sportcomplex;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        SportcomplexDao sportcomplexDao = new SportcomplexDao();
        req.setAttribute("scs", sportcomplexDao.selectSportcomplex());
        req.setAttribute("emp", employeeDao.selectEmployees());
        req.getRequestDispatcher("employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        SportcomplexDao sportcomplexDao = new SportcomplexDao();
        EmployeeDao employeeDao = new EmployeeDao();
        switch (action) {
            case "add": {
                try {
                    employeeDao.insertEmployee(Integer.parseInt(req.getParameter("sportcomplex")),
                            new Employee(req.getParameter("name"),
                                    new BigDecimal(req.getParameter("phone")),
                                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")).getTime()),
                                    req.getParameter("education"),
                                    Integer.parseInt(req.getParameter("salary")),
                                    req.getParameter("position"),
                                    Integer.parseInt(req.getParameter("exp")),
                                    req.getParameter("gender")));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                resp.sendRedirect(req.getContextPath() + "/employee");
                break;
            }
            case "edit": {
                Employee employee = employeeDao.findById(Integer.parseInt(req.getParameter("id")));
                employee.setFioEmployee(req.getParameter("name"));
                employee.setPhoneEmployee(new BigDecimal(req.getParameter("phone")));
                try {
                    employee.setBirthday(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                employee.setEducation(req.getParameter("education"));
                employee.setSalary(Integer.parseInt(req.getParameter("salary")));
                employee.setPositionEmp(req.getParameter("position"));
                employee.setWorkExp(Integer.parseInt(req.getParameter("exp")));
                employee.setGender(req.getParameter("gender"));
                employee.setFkSc(sportcomplexDao.findById(Integer.parseInt(req.getParameter("sportcomplex"))));
                employeeDao.updateEmployee(employee);
                resp.sendRedirect(req.getContextPath() + "/employee");
                break;
            }
            case "delete": {
                employeeDao.deleteEmployee(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/employee");
                break;
            }
        }
       /* req.setAttribute("sc", sportcomplexDao.selectSportcomplex());
         req.getRequestDispatcher("employee.jsp").forward(req, resp);*/
    }
}
