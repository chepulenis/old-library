package controller;

import dao.BookDAO;
import dao.BookDAOImplementation;
import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/BookController")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String ENCODING = "UTF-8";
    private BookDAO bookDAO;
    public static final String LIST_ALL_BOOKS = "/books.jsp";
    public static final String CREATE_OR_EDIT = "/createoreditbook.jsp";


    public BookController(){
        bookDAO = new BookDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        String forward = "";
        String action = request.getParameter("action");

        if( action.equalsIgnoreCase("delete")) {
            forward = LIST_ALL_BOOKS;
            int id = Integer.parseInt(request.getParameter("id"));
            bookDAO.removeBook(id);
            request.setAttribute("books", bookDAO.listAllBooks());
        }
        else if( action.equalsIgnoreCase("edit")) {
            forward = CREATE_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Book book = bookDAO.getBookById(id);
            request.setAttribute("book", book);
        }
        else if(action.equalsIgnoreCase("create")) {
            forward = CREATE_OR_EDIT;
        }
        else {
            forward = LIST_ALL_BOOKS;
            request.setAttribute("books", bookDAO.listAllBooks());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        Book book = new Book();
        book.setName(request.getParameter("name"));
        book.setDescription(request.getParameter("description"));
        book.setGenre(request.getParameter("genre"));
        book.setISBN(request.getParameter("ISBN"));
        book.setAddress(request.getParameter("address"));
        String id = request.getParameter("id");

        if(id == null || id.isEmpty())
            bookDAO.createBook(book);
        else {
            book.setId(Integer.parseInt(id));
            String dateCreated = request.getParameter("dateCreated");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date;
            try {
                date = sdf.parse(dateCreated);
                java.sql.Date sqlTakeDate = new java.sql.Date(date.getTime());
                book.setTakeDate(sqlTakeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bookDAO.editBook(book);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ALL_BOOKS);
        request.setAttribute("books", bookDAO.listAllBooks());
        view.forward(request, response);
    }
}
