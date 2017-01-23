package presentation;

import persistence.UserJdbcRepository;
import service.UserAddService;
import service.UserSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lukas on 1/16/17.
 */
@WebServlet (name ="create_user", urlPatterns = "/create_user")
public class UserAddServlet extends HttpServlet {
    private UserAddService service;

    public void init() throws ServletException {
        super.init();
        service = new UserAddService(new UserJdbcRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsps/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String username = req.getParameter("username");
       String password = req.getParameter("password");
       service.createUser(username,password);
       req.getRequestDispatcher("/WEB-INF/jsps/listUsers.jsp").forward(req, resp);
    }
}
