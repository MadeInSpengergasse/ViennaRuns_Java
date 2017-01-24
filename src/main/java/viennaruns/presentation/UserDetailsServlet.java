package viennaruns.presentation;

import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;
import viennaruns.service.UserDetailsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
@WebServlet(name = "details_user", urlPatterns = "/details_user")
public class UserDetailsServlet extends HttpServlet {

    private UserDetailsService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new UserDetailsService(new UserJdbcRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            handleRequest(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // req.getRequestDispatcher("/WEB-INF/jsps/detailsUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            handleRequest(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //req.getRequestDispatcher("/WEB-INF/jsps/listUsers.jsp").forward(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // FIXME: Check
        long uid = 0;
        try {
            uid = Long.parseLong(req.getParameter("uid"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("search");
        }

        Optional<User> opt = service.findUser(uid);
        if (opt.isPresent()) {
            User user = opt.get();
            req.setAttribute("username", user.getName());
            req.setAttribute("password", user.getPassword());
            req.getRequestDispatcher("/WEB-INF/jsps/detailsUser.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("search");
        }

    }
}