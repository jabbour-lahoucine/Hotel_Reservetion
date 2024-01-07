package dao;

import MyDb.DbConnection;
import entities.Client;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class reservationDao {

    public boolean isRoomAvailable(String categoryId, String checkInDateParam, String checkOutDateParam) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Connection connection = DbConnection.getCon()) {
            LocalDate checkInDate = LocalDate.parse(checkInDateParam, dateFormatter);
            LocalDate checkOutDate = LocalDate.parse(checkOutDateParam, dateFormatter);
            String query = "SELECT COUNT(*) " +
                    "FROM room r " +
                    "LEFT JOIN resevation res ON r.id = res.room_id " +
                    "WHERE r.room_category_id = ? AND (res.id IS NULL OR (res.checkout_Date < ? OR res.checkin_Date > ?))";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categoryId);
                preparedStatement.setDate(2, java.sql.Date.valueOf(checkInDate)); // assuming your database column is of type DATE
                preparedStatement.setDate(3, java.sql.Date.valueOf(checkOutDate));

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int overlappingReservations = resultSet.getInt(1);
                    return overlappingReservations != 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public int getAvailableRoom(String categoryId, String checkInDateParam, String checkOutDateParam) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (Connection connection = DbConnection.getCon()) {
            LocalDate checkInDate = LocalDate.parse(checkInDateParam, dateFormatter);

            // Parse check-out date
            LocalDate checkOutDate = LocalDate.parse(checkOutDateParam, dateFormatter);
            String query = "SELECT r.id " +
                    "FROM room r " +
                    "LEFT JOIN resevation res ON r.id = res.room_id " +
                    "WHERE r.room_category_id = ? AND (res.id IS NULL OR (res.checkout_Date < ? OR res.checkin_Date > ?))";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, categoryId);
                preparedStatement.setDate(2, java.sql.Date.valueOf(checkInDate));
                preparedStatement.setDate(3, java.sql.Date.valueOf(checkOutDate));

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int availableRoomId = resultSet.getInt("id");
                    System.out.println(availableRoomId);
                    return availableRoomId;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean insertReservation(String checkin, String checkout, String name, String email, int categoryId,String identification ,String phonNum) {
        ClientDao clientDao = new ClientDao();
        try (Connection connection = DbConnection.getCon()) {

            try {
                Client client = clientDao.getClientByEmail(email);
                String reservationQuery = "INSERT INTO resevation (checkin_date, checkout_date, client_id, room_id) VALUES (?, ?, ?, ?)";
                if (client != null){
                    try (PreparedStatement reservationStatement = connection.prepareStatement(reservationQuery)) {
                        reservationStatement.setDate(1, Date.valueOf(checkin));
                        reservationStatement.setDate(2, Date.valueOf(checkout));
                        reservationStatement.setLong(3, client.getId());
                        reservationStatement.setInt(4, this.getAvailableRoom(String.valueOf(categoryId), checkin, checkout));

                        int reservationRowsInserted = reservationStatement.executeUpdate();

                        if (reservationRowsInserted > 0) {
                            return true;
                        }
                    }
                }else{
                    Client client1 = new Client();
                    client1.setFullName(name);
                    client1.setEmail(email);
                    client1.setPhoneNumber(phonNum);
                    client1.setIdentification(identification);
                    clientDao.insertClient(client1);
                    try (PreparedStatement reservationStatement = connection.prepareStatement(reservationQuery)) {
                        reservationStatement.setDate(1, Date.valueOf(checkin));
                        reservationStatement.setDate(2, Date.valueOf(checkout));
                        reservationStatement.setLong(3, client1.getId());
                        reservationStatement.setInt(4, this.getAvailableRoom(String.valueOf(categoryId), checkin, checkout));

                        int reservationRowsInserted = reservationStatement.executeUpdate();

                        if (reservationRowsInserted > 0) {
                            return true;
                        }
                    }
                }
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
