package Train.Ticket.Train.Ticket.dao.repository;

import Train.Ticket.Train.Ticket.dao.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
//    @Query("SELECT s FROM Seat s WHERE s.trainClass.id = :trainClassId AND s.seatStatus = 'AVAILABLE'")
    List<Seat> findAvailableSeatsByTrainClassId(@Param("trainClassId") Long trainClassId);
}
