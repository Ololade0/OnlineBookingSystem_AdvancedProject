package Train.Ticket.Train.Ticket.dao.repository;

import Train.Ticket.Train.Ticket.dao.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
