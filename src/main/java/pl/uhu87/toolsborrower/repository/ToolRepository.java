package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Tool;
import pl.uhu87.toolsborrower.entity.User;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository <Tool, Long> {


    @Query("select t from Tool t order by t.name")
    List <Tool> findAllOderByname();

}
