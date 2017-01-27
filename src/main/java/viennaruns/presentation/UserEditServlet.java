package viennaruns.presentation;


import viennaruns.domain.User;
import viennaruns.persistence.UserJdbcRepository;
import viennaruns.service.UserEditService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by lukas on 1/24/17.
 */
@WebServlet(name = "edit_user", urlPatterns = "/edit_user")
public class UserEditServlet extends HttpServlet {
    private long uid;
    private UserEditService service;

    public void init() throws ServletException {
        super.init();
        service = new UserEditService(new UserJdbcRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            uid = findUserID(req);
            handleRequest(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/jsps/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Optional<User> u = service.findUser(uid);

            if (u.isPresent()) {
                User user = u.get();
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                user.setName(username);
                user.setPassword(password);
                service.updateUser(user);

            } else {
                resp.sendRedirect("search");



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("search");
    }


    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        Optional<User> opt = service.findUser(uid);
        if (opt.isPresent()) {
            User user = opt.get();
            req.setAttribute("username", user.getName());
            req.setAttribute("password", user.getPassword());

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
