package pl.uhu87.toolsborrower.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Borrowing;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {


    List<Borrowing> findAllByUserIdAndActiveTrue(Long borrower_id);
}

