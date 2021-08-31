package servlets;

import dao.ClientDao;
import entities.Client;

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

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDao clientDao = new ClientDao();
        req.setAttribute("clients", clientDao.selectClients());
        req.getRequestDispatcher("client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        ClientDao clientDao = new ClientDao();
        switch (action) {
            case "add": {
                try {
                    clientDao.saveClient(new Client(req.getParameter("name"), new Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")).getTime()),
                            req.getParameter("gender"), new BigDecimal(req.getParameter("phone"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Ошибка преобразования даты");
                }
                resp.sendRedirect(req.getContextPath() + "/client");
                break;
            }
            case "edit": {
                Client client = clientDao.findById(Integer.parseInt(req.getParameter("id")));
                client.setFioClient(req.getParameter("name"));
                try {
                    client.setBirthday(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday")).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                client.setGender(req.getParameter("gender"));
                clientDao.updateClient(client);
                resp.sendRedirect(req.getContextPath() + "/client");
                break;
            }
            case "delete": {
                clientDao.deleteClient(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/client");
                break;
            }
        }
        //req.setAttribute("sc", sportcomplexDao.selectSportcomplex());
        // req.getRequestDispatcher("sportcomplex.jsp").forward(req, resp);
    }
}
