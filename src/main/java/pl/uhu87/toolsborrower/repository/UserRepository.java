package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.id NOT like :givenId and u.active=true")
    List <User> findAllActiveButLogged (@Param("givenId") Long id);

    @Query(value= "select distinct user.username from user join user_tool ut on user.id = ut.user_id and ut.tool_id=:givenId", nativeQuery = true)
    List <String> uniqueToolUsers (@Param("givenId") Long id);


}
