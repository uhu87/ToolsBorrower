package pl.uhu87.toolsborrower;

import org.springframework.stereotype.Service;
import pl.uhu87.toolsborrower.entity.User;


@Service
public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}
