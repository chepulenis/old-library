package controller;

import dao.BookDAO;
import dao.BookDAOImplementation;
import model.Book;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns =  {"/BookController","/BookController.do"})
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String ENCODING = "UTF-8";
    private BookDAO bookDAO;
    public static final String LIST_ALL_BOOKS = "/books.jsp";
    public static final String CREATE_OR_EDIT = "/createoreditbook.jsp";
    public static final String ADD = "/borrowedbooks.jsp";




    public BookController(){
        bookDAO = new BookDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        int numberOfRecords = bookDAO.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        List<Book> list = bookDAO.listAllBooks((page-1)*recordsPerPage, recordsPerPage);
        request.setAttribute("books", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);


        String forward = "";
        String action = request.getParameter("action");

        if( action.equalsIgnoreCase("delete")) {
            forward = LIST_ALL_BOOKS;
            int id = Integer.parseInt(request.getParameter("id"));
            bookDAO.removeBook(id);
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
        else if(action.equalsIgnoreCase("add")) {
            forward = ADD;
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new User();
            user.setBookID(id);
        }
        else {
            forward = LIST_ALL_BOOKS;
            request.setAttribute("books", list);
        }


        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(ENCODING);
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        int numberOfRecords = bookDAO.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
        List<Book> list = bookDAO.listAllBooks((page-1)*recordsPerPage, recordsPerPage);
        request.setAttribute("books", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        Book book = new Book();
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setDescription(request.getParameter("description"));
        book.setGenre(request.getParameter("genre"));
        book.setISBN(request.getParameter("ISBN"));
        book.setAddress(request.getParameter("address"));
        String id = request.getParameter("id");

        if(id == null || id.isEmpty())
            bookDAO.createBook(book);
        else {
            book.setId(Integer.parseInt(id));
            String takeDate = request.getParameter("takeDate");
            String expirationDate = request.getParameter("expirationDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date;
            try {
                date = sdf.parse(takeDate);
                java.sql.Date sqlTakeDate = new java.sql.Date(date.getTime());
                book.setTakeDate(sqlTakeDate);
                date = sdf.parse(expirationDate);
                java.sql.Date sqlExpirationDate = new java.sql.Date(date.getTime());
                book.setExpirationDate(sqlExpirationDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bookDAO.editBook(book);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ALL_BOOKS);
        request.setAttribute("books", list);
        view.forward(request, response);
    }
}
