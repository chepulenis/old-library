package dao;

import model.Book;

import java.util.List;

public interface BookDAO {
    public void createBook (Book book);
    public void editBook (Book book);
    public void removeBook (int id);
    public List<Book> listAllBooks();
    public Book getBookById (int id);
}
