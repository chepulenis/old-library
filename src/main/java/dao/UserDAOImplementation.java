package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO {
    private Connection conn;

    public UserDAOImplementation(){
        conn = util.DBConnection.initializeDatabase();
    }

    @Override
    public void createUser(User user) {
        String query = "insert into users (id, name, phoneNumber, email) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt( 1, user.getId() );
            preparedStatement.setString( 2, user.getName() );
            preparedStatement.setInt( 3, user.getPhoneNumber() );
            preparedStatement.setString( 4, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) {
        String query = "update users set name=?, phoneNumber=?, email=?, bookID=?, where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query);) {
            preparedStatement.setString( 1, user.getName() );
            preparedStatement.setInt(2, user.getPhoneNumber() );
            preparedStatement.setString( 3, user.getEmail() );
            preparedStatement.setInt(5, user.getId() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(int id) {
        String query = "delete from users where id=?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listAllUsers() {
        List<User> users = new ArrayList<>();
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from users" )) {

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getInt("phoneNumber"));
                user.setEmail(resultSet.getString("email"));
//                user.setBookID(resultSet.getInt("bookID"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User getUserById(int id) {
        User user = new User();
        String query = "select * from users where id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getInt("phoneNumber"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getInt("bookID"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
