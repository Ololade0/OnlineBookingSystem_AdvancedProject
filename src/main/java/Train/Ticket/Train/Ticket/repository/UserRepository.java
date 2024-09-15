package Train.Ticket.Train.Ticket.repository;
import Train.Ticket.Train.Ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
