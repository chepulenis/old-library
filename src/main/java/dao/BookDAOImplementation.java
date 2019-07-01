package dao;

import model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImplementation implements BookDAO{

    private Connection conn;
    private int numberOfRecords;

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public BookDAOImplementation(){
        conn = util.DBConnection.initializeDatabase();
    }

    @Override
    public void createBook(Book book) {
        String query = "insert into books (id, title, author, description, genre, ISBN, address, takeDate, expirationDate) values (?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt( 1, book.getId() );
            preparedStatement.setString( 2, book.getTitle() );
            preparedStatement.setString( 3, book.getAuthor() );
            preparedStatement.setString( 4, book.getDescription() );
            preparedStatement.setString( 5, book.getGenre() );
            preparedStatement.setString(6, book.getISBN());
            preparedStatement.setString(7, book.getAddress());

            LocalDate localDate = LocalDate.now();
            java.sql.Date sqlDate = new java.sql.Date(Date.valueOf(localDate).getTime());
            book.setTakeDate(sqlDate);
            preparedStatement.setDate(8, sqlDate);

            sqlDate = new java.sql.Date(Date.valueOf(localDate.plusMonths(1)).getTime());
            book.setExpirationDate(sqlDate);
            preparedStatement.setDate(9,sqlDate);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBook(Book book) {
        String query = "update books set title=?, author-? description=?, genre=?, ISBN=?, address=? where id=?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString( 1, book.getTitle() );
            preparedStatement.setString( 2, book.getDescription() );
            preparedStatement.setString( 3, book.getAuthor() );
            preparedStatement.setString(4, book.getGenre() );
            preparedStatement.setString( 5, book.getISBN());
            preparedStatement.setString( 6, book.getAddress() );


            preparedStatement.setInt(7, book.getId() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBook(int id) {
        String query = "delete from books where id=?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Book> listAllBooks(int offset, int numberOfRecords) {
        List<Book> books = new ArrayList<>();
        try(Statement statement = conn.createStatement())
        {
            ResultSet resultSet = statement.executeQuery( "select SQL_CALC_FOUND_ROWS * from books limit "
                    + offset + ", " + numberOfRecords );
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setAddress(resultSet.getString("address"));
                book.setTakeDate(resultSet.getDate("takeDate"));
                book.setExpirationDate(resultSet.getDate("expirationDate"));
                books.add(book);
            }
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()){
                this.numberOfRecords = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    @Override
    public Book getBookById(int id) {
        Book book = new Book();
        String query = "select * from books where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));
                book.setGenre(resultSet.getString("genre"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setAddress(resultSet.getString("address"));
                book.setTakeDate(resultSet.getDate("takeDate"));
                book.setExpirationDate(resultSet.getDate("expirationDate"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static void proceedResultSet(ResultSet resultSet){
        Book book = new Book();

    }
}
