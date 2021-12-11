package pl.uhu87.toolsborrower.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Podaj imię")
    private String firstName;
    @NotEmpty(message = "Podaj nazwisko")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<UserTool> userTools = new ArrayList<>();

    @NotEmpty(message = "Podaj nazwę użytkownika")
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    @NotEmpty(message = "musisz podać password")
    private String password;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private String phone;
    @Email(message = "Podaj poprawny adres email aby inni użytkownicy mogli się z Tobą skontaktować")
    private String email;
    private boolean active = true;

    public User(){}

    public User(Long id, String username, String password, int enabled, Set<Role> roles, String phone,String email, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.phone = phone;
        this.email = email;
        this.active= active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
