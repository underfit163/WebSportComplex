package servlets;

import dao.ClientDao;
import dao.DisciplineDao;
import entities.Client;
import entities.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/discipline")
public class DisciplineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DisciplineDao disciplineDao = new DisciplineDao();
        req.setAttribute("disc", disciplineDao.selectDiscipline());
        req.setAttribute("check","false");
        req.getRequestDispatcher("discipline.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        DisciplineDao disciplineDao = new DisciplineDao();
        switch (action) {
            case "add": {
                try {
                    disciplineDao.saveDiscipline(new Discipline(req.getParameter("name"), new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("dateStart")).getTime()),
                            new BigDecimal(req.getParameter("numberDis")), Integer.parseInt(req.getParameter("price"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                resp.sendRedirect(req.getContextPath() + "/discipline");
                break;
            }
            case "edit": {
                Discipline discipline = disciplineDao.findById(Integer.parseInt(req.getParameter("id")));
                discipline.setTypeDiscipline(req.getParameter("name"));
                try {
                    discipline.setDateStart(new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("dateStart")).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                discipline.setNumberDiscipline(new BigDecimal(req.getParameter("numberDis")));
                discipline.setPrice(Integer.parseInt(req.getParameter("price")));
                disciplineDao.updateDiscipline(discipline);
                resp.sendRedirect(req.getContextPath() + "/discipline");
                break;
            }
            case "delete": {
                disciplineDao.deleteDiscipline(Integer.parseInt(req.getParameter("id")));

                break;
            }
            case "request":{
                req.setAttribute("disc", disciplineDao.selectDiscipline());
                req.setAttribute("check","true");
                req.setAttribute("prices",disciplineDao.selectDisByPrice(Integer.parseInt(req.getParameter("pr1")),Integer.parseInt(req.getParameter("pr2"))));
                req.getRequestDispatcher("discipline.jsp").forward(req, resp);
                break;
            }
        }
        //req.setAttribute("sc", sportcomplexDao.selectSportcomplex());
        // req.getRequestDispatcher("sportcomplex.jsp").forward(req, resp);
    }
}
