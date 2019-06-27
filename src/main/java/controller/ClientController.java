package controller;

import dao.ClientDAO;
import dao.ClientDAOImplementation;
import model.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ClientController")
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String ENCODING = "UTF-8";
    private ClientDAO clientDAO;
    public static final String LIST_ALL_CLIENTS = "/clients.jsp";
    public static final String CREATE_OR_EDIT = "/createoreditclient.jsp";


    public ClientController(){
        clientDAO = new ClientDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        String forward = "";
        String action = request.getParameter("action");

        if( action.equalsIgnoreCase("delete")) {
            forward = LIST_ALL_CLIENTS;
            int id = Integer.parseInt(request.getParameter("id"));
            clientDAO.removeClient(id);
            request.setAttribute("clients", clientDAO.listAllClients());
        }
        else if( action.equalsIgnoreCase("edit")) {
            forward = CREATE_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Client client = clientDAO.getClientById(id);
            request.setAttribute("client", client);
        }
        else if(action.equalsIgnoreCase("create")) {
            forward = CREATE_OR_EDIT;
        }
        else {
            forward = LIST_ALL_CLIENTS;
            request.setAttribute("clients", clientDAO.listAllClients());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        Client client = new Client();
        client.setName(request.getParameter("name"));
        client.setEmail(request.getParameter("email"));
        String phoneNumber = request.getParameter("phoneNumber");
        int number = (phoneNumber!=null) ? Integer.parseInt(phoneNumber) : 0;
            client.setPhoneNumber(number);
        String id = request.getParameter("id");

        if(id == null || id.isEmpty())
            clientDAO.createClient(client);
        else {
            client.setId(Integer.parseInt(id));
            clientDAO.editClient(client);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ALL_CLIENTS);
        request.setAttribute("clients", clientDAO.listAllClients());
        view.forward(request, response);
    }
}
