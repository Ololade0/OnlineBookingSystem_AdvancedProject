package Train.Ticket.Train.Ticket.dao.repository;

import Train.Ticket.Train.Ticket.dao.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(Long scheduleId);

}
