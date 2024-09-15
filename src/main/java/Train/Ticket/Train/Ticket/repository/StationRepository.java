package Train.Ticket.Train.Ticket.repository;

import Train.Ticket.Train.Ticket.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
