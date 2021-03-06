package pl.uhu87.toolsborrower.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserTool userTool;    //dane od LENDERA

    @ManyToOne
    @JoinColumn(name = "borrower")
    private User user;

    private LocalDate start;
    private  LocalDate end;

    private boolean active = true;

    private String notification;
    private String ownerInfo;
    private boolean reservationDayOn = false;

    public Reservation() {
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public Reservation(Long id, UserTool userTool, User user, LocalDate start,
                       LocalDate end, boolean active, String notification,
                       boolean reservationDayOn, String ownerInfo) {
        this.id = id;
        this.userTool = userTool;
        this.user = user;
        this.start = start;
        this.end = end;
        this.active = active;
        this.notification = notification;
        this.reservationDayOn = reservationDayOn;
        this.ownerInfo = ownerInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTool getUserTool() {
        return userTool;
    }

    public void setUserTool(UserTool userTool) {
        this.userTool = userTool;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public boolean isReservationDayOn() {
        return reservationDayOn;
    }

    public void setReservationDayOn(boolean reservationDayOn) {
        this.reservationDayOn = reservationDayOn;
    }

    public LocalDate getEnd() {
        return end;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userTool=" + userTool +
                ", user=" + user +
                ", start=" + start +
                ", end=" + end +
                ", active=" + active +
                '}';
    }
}

