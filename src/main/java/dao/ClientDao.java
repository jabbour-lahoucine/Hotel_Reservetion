package dao;

import MyDb.DbConnection;
import entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public int getNumberClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DbConnection.getCon()) {
            String query = "SELECT * FROM client";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getInt("id"));
                    clients.add(client);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return clients.size();
    }
    public Client getClientByEmail(String email) {
        try (Connection connection = DbConnection.getCon()) {
            String query = "SELECT * FROM client WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getLong("id"));
                    client.setEmail(resultSet.getString("email"));
                    client.setIdentification(resultSet.getString("identification"));
                    client.setPhoneNumber(resultSet.getString("phone_num"));
                    client.setFullName(resultSet.getString("fullname"));
                    return client;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long insertClient(Client client) {
        try (Connection connection = DbConnection.getCon()) {
            String query = "INSERT INTO client (email, identification, phone_num, fullname) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, client.getEmail());
                preparedStatement.setString(2, client.getIdentification());
                preparedStatement.setString(3, client.getPhoneNumber());
                preparedStatement.setString(4, client.getFullName());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }
}