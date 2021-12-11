package pl.uhu87.toolsborrower;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.uhu87.toolsborrower.entity.User;

import java.util.List;


@Service
public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);


}
