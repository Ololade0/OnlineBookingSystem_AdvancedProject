package Train.Ticket.Train.Ticket.dao.repository;

import Train.Ticket.Train.Ticket.dao.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainId(Long id);





}
