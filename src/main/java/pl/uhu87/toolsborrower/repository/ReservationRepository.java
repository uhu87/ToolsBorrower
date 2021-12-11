package pl.uhu87.toolsborrower.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.uhu87.toolsborrower.entity.Reservation;
import pl.uhu87.toolsborrower.entity.User;
import pl.uhu87.toolsborrower.entity.UserTool;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserToolId(Long id);
    List<Reservation> findAllByUserToolIdAndActiveTrueOrderByStart(Long id);
    List<Reservation> findAllByUserToolIdOrderByStart(Long id);

    @Query(value = "select * from reservation where reservation.user_tool_id = :givenId and active=true order by start limit 1", nativeQuery = true)
    Reservation findEarliestActiveReservation (@Param("givenId") Long id);

    List <Reservation> findAllByUserAndActiveTrue (User user);
    List <Reservation> findAllByUserAndActiveTrueOrderByStartAsc (User user);

    @Query("select r from Reservation r where r.userTool.present = true and r.user.id= :givenId and r.active=true order by r.start asc")
    List<Reservation> findAllActiveAndToolsPresent (@Param("givenId") Long id);




    @Query("select r from Reservation r where r.userTool.present = false and r.user.id= :givenId and r.active=true order by r.start")
    List<Reservation> findReservationsWithToolsNotPresent (@Param("givenId") Long id);


}
