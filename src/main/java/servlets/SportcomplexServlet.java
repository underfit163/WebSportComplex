package servlets;

import dao.SportcomplexDao;
import entities.Sportcomplex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/sportcomplex")
public class SportcomplexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SportcomplexDao sportcomplexDao = new SportcomplexDao();
        req.setAttribute("scs", sportcomplexDao.selectSportcomplex());
        req.getRequestDispatcher("sportcomplex.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        SportcomplexDao sportcomplexDao = new SportcomplexDao();
        switch (action) {
            case "add": {
                sportcomplexDao.saveSportcomplex(new Sportcomplex(req.getParameter("name"),
                        new BigDecimal(req.getParameter("phone")),
                        req.getParameter("address")));
                resp.sendRedirect(req.getContextPath() + "/sportcomplex");
                break;
            }
            case "edit": {
                Sportcomplex sc = sportcomplexDao.findById(Integer.parseInt(req.getParameter("id")));
                sc.setNameSc(req.getParameter("name"));
                sc.setPhoneSc(new BigDecimal(req.getParameter("phone")));
                sc.setAddressSc(req.getParameter("address"));
                sportcomplexDao.updateSportcomplex(sc);
                resp.sendRedirect(req.getContextPath() + "/sportcomplex");
                break;
            }
            case "delete": {
                sportcomplexDao.deleteSportcomplex(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/sportcomplex");
                break;
            }
        }
        //req.setAttribute("sc", sportcomplexDao.selectSportcomplex());
        // req.getRequestDispatcher("sportcomplex.jsp").forward(req, resp);
    }
}
