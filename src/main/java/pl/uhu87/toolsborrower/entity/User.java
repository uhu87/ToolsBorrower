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

    @ManyToMany(mappedBy = "users")
    private List<Tool> tools = new ArrayList<>();

    public User(){}

    public User(Long id, String firstName, String lastName, List<Tool> tools) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tools = tools;
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

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "" + firstName + ' '+ lastName;
    }
}
