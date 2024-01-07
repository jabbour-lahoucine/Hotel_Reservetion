package entities;

public class RoomCategory {
    private long id;
    private String name;
    private double price;
    private String roomImage;
    private long roomId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public long getRoom() {
        return roomId;
    }

    public void setRoom(long roomId) {
        this.roomId = roomId;
    }
}
