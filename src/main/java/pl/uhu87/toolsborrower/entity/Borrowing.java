package pl.uhu87.toolsborrower.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserTool userTool;    //dane od LENDERA

    @ManyToOne
    @JoinColumn(name = "borrower")
    private User user;              // BORROWER

    private boolean active = true;

    private LocalDate start;
    private LocalDate end;


    public Borrowing(Long id, UserTool userTool, User user, boolean active, LocalDate Start, LocalDate end) {
        this.id = id;
        this.userTool = userTool;
        this.user = user;
        this.active = active;
        this.end = end;
        this.start = LocalDate.now();
    }

    public Borrowing() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
