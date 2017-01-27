package viennaruns.presentation;

import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;
import viennaruns.service.UserDeleteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by lukas on 1/23/17.
 */
@WebServlet(name = "delete_user", urlPatterns = "/delete_user")
public class UserDeleteServlet extends HttpServlet {
    private UserDeleteService service;
    private long uid;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new UserDeleteService(new UserJdbcRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            uid = findUserID(req);
            handleRequest(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/jsps/deleteUser.jsp").forward(req, resp);
        // req.getRequestDispatcher("/WEB-INF/jsps/detailsUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.deleteUser(uid);


        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("search");
        //req.getRequestDispatcher("/WEB-INF/jsps/listUsers.jsp").forward(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        Optional<User> opt = service.findUser(uid);
        if (opt.isPresent()) {
            User user = opt.get();
            req.setAttribute("username", user.getName());

        } else {
            resp.sendRedirect("search");
        }

    }

    private long findUserID(HttpServletRequest req) throws Exception {
        long uid = 0;
        try {
            uid = Long.parseLong(req.getParameter("uid"));
        } catch (NumberFormatException e) {

        }
        System.out.println(uid);
        return uid;
    }
}
