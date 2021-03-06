package pl.uhu87.toolsborrower.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;

import java.util.List;

@Repository
public interface UserToolRepository extends JpaRepository <UserTool, Long> {

    public List<UserTool> findAllByToolId(Long toolId);
    public List<UserTool> findAllByUser(User user);
    public List<UserTool> findAllByUserAndPresentTrue(User user);

    public List<UserTool> findAllByUserAndAvailableTrueAndPresentTrue(User user);
    public List<UserTool> findAllByUserAndAvailableFalseAndPresentTrue(User user);

    public UserTool getById(Long id);

}
