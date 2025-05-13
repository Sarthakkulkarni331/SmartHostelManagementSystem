package model;

public class Room {
    private int RoomId;
    private String guestName;
    private String status;

    public Room(int roomId, String guestName, String status) {
        this.RoomId = roomId;
        this.guestName = guestName;
        this.status = status;
    }

    // Getters
    public int getRoomId() {
        return RoomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}