package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByTools(Tool tool);

}
