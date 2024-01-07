package dao;

import MyDb.DbConnection;
import entities.RoomCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class RoomCategoryDao {
    public List<RoomCategory> getAllRoomCategories() {
        List<RoomCategory> roomCategories = new ArrayList<>();
        try (Connection connection = DbConnection.getCon()) {
            String query = "SELECT * FROM room_category";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    RoomCategory category = new RoomCategory();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    category.setPrice(resultSet.getDouble("price"));
                    Blob imageBlob = resultSet.getBlob("room_image");
                    if (imageBlob != null) {
                        byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                        String base64Image = Base64.getEncoder().encodeToString(imageData);
                        category.setRoomImage(base64Image);
                        System.out.println("Base64 Image: " + base64Image);
                    }

                    roomCategories.add(category);
                }
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return roomCategories;
    }
}
