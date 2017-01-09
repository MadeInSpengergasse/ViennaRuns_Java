package presentation;

import domain.User;
import persistence.UserJdbcRepository;
import service.UserSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
@WebServlet(name = "search", urlPatterns = "/search")
public class UserSearchServlet extends HttpServlet {

    private UserSearchService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new UserSearchService(new UserJdbcRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchParam = req.getParameter("search");
        List<User> users = service.search(searchParam);

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsps/listUsers.jsp").forward(req, resp);

    }
}
