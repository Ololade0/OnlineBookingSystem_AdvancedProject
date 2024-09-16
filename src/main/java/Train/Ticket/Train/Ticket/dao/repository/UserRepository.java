package Train.Ticket.Train.Ticket.dao.repository;
import Train.Ticket.Train.Ticket.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
