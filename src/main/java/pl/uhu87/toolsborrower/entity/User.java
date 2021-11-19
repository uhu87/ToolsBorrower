package pl.uhu87.toolsborrower.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<UserTool> userTools = new ArrayList<>();

    public User(){}

    public User(Long id, String firstName, String lastName, List<UserTool> userTools) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userTools = userTools;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserTool> getUserTools() {
        return userTools;
    }

    public void setUserTools(List<UserTool> userTools) {
        this.userTools = userTools;
    }

    @Override
    public String toString() {
        return "" + firstName + ' '+ lastName;
    }
}
