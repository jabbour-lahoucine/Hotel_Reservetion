package entities;

import java.time.LocalDateTime;

public class Reservation {

    private long id;
    private LocalDateTime chechInDate;
    private LocalDateTime chechOutDate;

    private long clientId;
    private long roomId;
    private long managerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getChechInDate() {
        return chechInDate;
    }

    public void setChechInDate(LocalDateTime chechInDate) {
        this.chechInDate = chechInDate;
    }

    public LocalDateTime getChechOutDate() {
        return chechOutDate;
    }

    public void setChechOutDate(LocalDateTime chechOutDate) {
        this.chechOutDate = chechOutDate;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}
