package pl.uhu87.toolsborrower.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Borrowing;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {


    List<Borrowing> findAllByUserIdAndActiveTrue(Long borrower_id);
    Borrowing findFirstByUserToolIdAndActiveTrue(Long tooLId);

    @Query("select b from Borrowing b where b.userTool.user.id = :lender_id and b.active=true")
    List<Borrowing> findAllLentbyLenderId (Long lender_id);

    List<Borrowing> findAllByUserToolId(Long id);
    List<Borrowing> findAllByUserToolIdOrderByEndDesc(Long id);
    List<Borrowing> findAllByUserToolIdAndActiveTrue(Long id);
}

