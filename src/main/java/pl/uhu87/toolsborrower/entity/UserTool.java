package pl.uhu87.toolsborrower.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class UserTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    private Long tool_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTool_id() {
        return tool_id;
    }

    public void setTool_id(Long tool_id) {
        this.tool_id = tool_id;
    }

    @Override
    public String toString() {
        return ""+tool_id;
    }
}
