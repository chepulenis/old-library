package dao;

import model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImplementation implements BookDAO{

    private Connection conn;

    public BookDAOImplementation(){
        conn = util.DBConnection.initializeDatabase();
    }

    @Override
    public void createBook(Book book) {
        try {
            String query = "insert into books (id, name, description, genre, ISBN, address, takeDate, expirationDate) values (?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt( 1, book.getId() );
            preparedStatement.setString( 2, book.getName() );
            preparedStatement.setString( 3, book.getDescription() );
            preparedStatement.setString( 4, book.getGenre() );
            preparedStatement.setString(5, book.getISBN());
            preparedStatement.setString(6, book.getAddress());

            LocalDate localDate = LocalDate.now();
            java.sql.Date sqlDate = new java.sql.Date(Date.valueOf(localDate).getTime());
            book.setTakeDate(sqlDate);
            preparedStatement.setDate(7, sqlDate);

            sqlDate = new java.sql.Date(Date.valueOf(localDate.plusMonths(1)).getTime());
            book.setExpirationDate(sqlDate);
            preparedStatement.setDate(8,sqlDate);
            

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBook(Book book) {
        try {
            String query = "update books set name=?, description=?, genre=?, ISBN=?, address=?, takeDate=?, expirationDate=? where id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString( 1, book.getName() );
            preparedStatement.setString( 2, book.getDescription() );
            preparedStatement.setString(3, book.getGenre() );
            preparedStatement.setString( 4, book.getISBN() );
            preparedStatement.setString( 5, book.getAddress() );


            preparedStatement.setInt(6, book.getId() );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBook(int id) {
        try {
            String query = "delete from books where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from books" );
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setAddress(resultSet.getString("address"));
                book.setTakeDate(resultSet.getDate("takeDate"));
                book.setExpirationDate(resultSet.getDate("expirationDate"));
                books.add(book);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = new Book();
        try {
            String query = "select * from books where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setAddress(resultSet.getString("address"));
                book.setTakeDate(resultSet.getDate("takeDate"));
                book.setExpirationDate(resultSet.getDate("expirationDate"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
