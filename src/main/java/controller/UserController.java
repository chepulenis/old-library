package controller;

import dao.UserDAO;
import dao.UserDAOImplementation;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = {"/UserController", "/UserController.do"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String ENCODING = "UTF-8";
    private UserDAO userDAO;
    public static final String LIST_ALL_USERS = "/users.jsp";
    public static final String CREATE_OR_EDIT = "/createoredituser.jsp";


    public UserController(){
        userDAO = new UserDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        String forward = "";
        String action = request.getParameter("action");

        if( action.equalsIgnoreCase("delete")) {
            forward = LIST_ALL_USERS;
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.removeUser(id);
            request.setAttribute("users", userDAO.listAllUsers());
        }
        else if( action.equalsIgnoreCase("edit")) {
            forward = CREATE_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDAO.getUserById(id);
            request.setAttribute("user", user);
        }
        else if(action.equalsIgnoreCase("create")) {
            forward = CREATE_OR_EDIT;
        }
        else {
            forward = LIST_ALL_USERS;
            request.setAttribute("users", userDAO.listAllUsers());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        String phoneNumber = request.getParameter("phoneNumber");
        int number = (phoneNumber!=null) ? Integer.parseInt(phoneNumber) : 0;
        user.setPhoneNumber(number);
        String id = request.getParameter("id");

        if(id == null || id.isEmpty())
            userDAO.createUser(user);
        else {
            user.setId(Integer.parseInt(id));
            userDAO.editUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ALL_USERS);
        request.setAttribute("users", userDAO.listAllUsers());
        view.forward(request, response);
    }
}
