package pl.uhu87.toolsborrower.entity;


import javax.persistence.*;

@Entity
public class UserTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tool tool;

                            /*  (to USER_TOOL ma klucz obcy od TOOL (tool category),
                                // tutaj JOIN COLUMN z info jak nazwac ten klucz,
                             Jest to adnotacja opcjonalna – za jej pomocą określamy nazwę klucza obcego.
                             W przypadku braku zdefiniowanej nazwy, klucz obcy otrzyma nazwę składającą się
                             z nazwy pola, znaku podkreślenia oraz nazwy klucza drugiej encji. */


    private String description;


    private boolean availible;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailible() {
        return availible;
    }

    public void setAvailible(boolean availible) {
        this.availible = availible;
    }

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

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public String toString() {
        return ""+tool.getName();
    }
}
