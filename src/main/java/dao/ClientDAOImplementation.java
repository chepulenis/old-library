package dao;

import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImplementation implements ClientDAO {
    private Connection conn;

    public ClientDAOImplementation(){
        conn = util.DBConnection.initializeDatabase();
    }

    @Override
    public void createClient(Client client) {
        try {
            String query = "insert into clients (id, name, phoneNumber, email) values (?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt( 1, client.getId() );
            preparedStatement.setString( 2, client.getName() );
            preparedStatement.setInt( 3, client.getPhoneNumber() );
            preparedStatement.setString( 4, client.getEmail());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editClient(Client client) {
        try {
            String query = "update clients set name=?, phoneNumber=?, email=? where id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString( 1, client.getName() );
            preparedStatement.setInt(2, client.getPhoneNumber() );
            preparedStatement.setString( 3, client.getEmail() );

            preparedStatement.setInt(4, client.getId() );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeClient(int id) {
        try {
            String query = "delete from clients where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> listAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from clients" );
            while(resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPhoneNumber(resultSet.getInt("phoneNumber"));
                client.setEmail(resultSet.getString("email"));
                clients.add(client);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client getClientById(int id) {
        Client client = new Client();
        try {
            String query = "select * from clients where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPhoneNumber(resultSet.getInt("phoneNumber"));
                client.setEmail(resultSet.getString("email"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
