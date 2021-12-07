package pl.uhu87.toolsborrower.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserToolId(Long id);
    List<Reservation> findAllByUserToolIdAndActiveTrue(Long id);

}
