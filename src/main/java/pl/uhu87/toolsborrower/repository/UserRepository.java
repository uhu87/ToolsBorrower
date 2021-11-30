package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.id NOT like :givenId")
    List <User> findAllbutLogged (@Param("givenId") Long id);
}
