package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Role;
import pl.uhu87.toolsborrower.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //User findByUsername(String username);
    Role findByName(String name);
}
