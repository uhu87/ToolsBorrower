package pl.uhu87.toolsborrower.entity;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User {

    private final pl.uhu87.toolsborrower.entity.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.uhu87.toolsborrower.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.uhu87.toolsborrower.entity.User getUser() {return user;}
}