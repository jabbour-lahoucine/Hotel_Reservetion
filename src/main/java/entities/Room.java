package entities;

public class Room {
    private long id;
    private int number;
    private boolean availability;
    private long reservationId;
    private long roomCategoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(long roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }
}
