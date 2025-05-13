package dao;

import database.DBConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void checkIn(Room r) {
        String query = "INSERT INTO rooms (guest_name, status) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, r.getGuestName());
            pst.setString(2, r.getStatus());
            pst.executeUpdate();

            System.out.println("Guest " + r.getGuestName() + " checked in successfully.");

        } catch (SQLException e) {
            System.out.println("Check-in error: " + e.getMessage());
        }
    }

    public void checkOut(int roomId) {
        String query = "UPDATE rooms SET guest_name = NULL, status = 'Empty' WHERE room_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, roomId);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Room " + roomId + " checked out successfully.");
            } else {
                System.out.println("Room not found.");
            }

        } catch (SQLException e) {
            System.out.println("Check-out error: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM rooms";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("room_id");
                String name = rs.getString("guest_name");
                String status = rs.getString("status");

                Room r = new Room(id, name, status);
                roomList.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }

        return roomList;
    }
}