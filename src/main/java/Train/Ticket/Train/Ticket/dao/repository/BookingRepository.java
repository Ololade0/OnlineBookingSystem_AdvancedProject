package Train.Ticket.Train.Ticket.dao.repository;

import Train.Ticket.Train.Ticket.dao.model.Booking;
import Train.Ticket.Train.Ticket.service.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


}
