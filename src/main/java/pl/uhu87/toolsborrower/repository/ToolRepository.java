package pl.uhu87.toolsborrower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Tool;

@Repository
public interface ToolRepository extends JpaRepository <Tool, Long> {
}
