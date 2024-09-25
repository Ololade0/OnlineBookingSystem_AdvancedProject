package Train.Ticket.Train.Ticket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import train.booking.trainbooking.dao.model.Seat;
import train.booking.trainbooking.dao.model.SeatStatus;
import train.booking.trainbooking.dao.model.TrainClass;
import train.booking.trainbooking.dao.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    /**
     * Generate seats for a given train class based on seat number range.
     *
     * @param trainClass The train class to which seats are assigned
     * @param startSeatNumber The starting seat number
     * @param endSeatNumber The ending seat number
     */
    @Transactional
    public void generateSeatsForTrainClass(TrainClass trainClass, int startSeatNumber, int endSeatNumber) {
        List<Seat> seats = new ArrayList<>();
        for (int i = startSeatNumber; i <= endSeatNumber; i++) {
            Seat seat = Seat.builder()
                    .seatNumber(i)
                    .seatStatus(SeatStatus.UNOCCUPIED) // Set default status
                    .trainClass(trainClass) // Associate the seat with the train class
                    .build();
            seats.add(seat);
        }
        seatRepository.saveAll(seats); // Save generated seats
        trainClass.setSeats(seats); // Link generated seats to the train class
        log.info("Generated and saved seats for train class: " + trainClass.getClassName());
    }


}
