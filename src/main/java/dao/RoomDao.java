package dao;

import MyDb.DbConnection;
import entities.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public int getNumberRoom() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DbConnection.getCon()) {
            String query = "SELECT * FROM room";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setId(resultSet.getInt("id"));
                    room.setNumber(resultSet.getInt("number"));
                    room.setAvailability(resultSet.getBoolean("availability"));

                    rooms.add(room);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return rooms.size();
    }

}
