package ru.job4j.layoutPresentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.layoutLogic.ValidateService;
import ru.job4j.layoutPersistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SigninController extends HttpServlet {

    /**.
     * Is link for instance of the ValidateService class
     */
    private final ValidateService logic = ValidateService.getInstance();

    /**.
     * Is storage for keeping all users
     */
    private final List<User> storage = logic.getListStorage();

    /**.
     * Logger for this class
     */
    private final Logger logger = LoggerFactory.getLogger(SigninController.class);

    /**.
     * Method for getting info about client
     * @param req is question
     * @param resp is answer
     * @throws ServletException my be exception
     * @throws IOException it's too
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    /**.
     * Method for checking authorisation user
     * @param req is question
     * @param resp is answer
     * @throws ServletException my be exception
     * @throws IOException it's too
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (logic.isCredentional(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            if (logic.isAdmin(login, password)) {
                session.setAttribute("role", "admin");
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional is invalid");
            doGet(req, resp);
        }
    }
}
