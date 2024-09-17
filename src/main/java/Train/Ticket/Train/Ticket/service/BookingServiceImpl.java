package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.*;
import Train.Ticket.Train.Ticket.dao.repository.BookingRepository;
import Train.Ticket.Train.Ticket.dao.repository.SeatRepository;
import Train.Ticket.Train.Ticket.dto.request.BookingRequest;
import Train.Ticket.Train.Ticket.exception.NoSeatAvailableException;
import Train.Ticket.Train.Ticket.exception.TrainClassCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TrainService trainService;
    private final TrainClassService trainClassService;
    private final ScheduleService scheduleService;
    private final SeatRepository seatRepository;





    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {
      TrainClass foundTrainClass = trainClassService.findById(bookingRequest.getTrainClassId())
                .orElseThrow(() -> new IllegalArgumentException("Train class not found for ID: " + bookingRequest.getTrainClassId()));

        Schedule foundSchedule = scheduleService.findSchedule(bookingRequest.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found for ID: " + bookingRequest.getScheduleId()));
        List<Seat> availableSeats = seatRepository.findAvailableSeatsByTrainClassId(foundTrainClass.getId());
        List<Integer> requestedSeatNumbers = bookingRequest.getSelectedSeatNumber();
        List<Seat> selectedSeats = new ArrayList<>();

        for (Integer seatNumber : requestedSeatNumbers) {
            Seat seat = availableSeats.stream()
                    .filter(s -> s.getSeatNumber() == seatNumber)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Seat " + seatNumber + " is not available"));
            selectedSeats.add(seat);
        }
        for (Seat seat : selectedSeats) {
            seat.setSeatStatus(SeatStatus.OCCUPIED);
            seatRepository.save(seat); // Persist seat status change
        }

        // Step 4: Create Booking
        Booking newBooking = Booking.builder()
                .seatNumbers(requestedSeatNumbers.stream().map(String::valueOf).collect(Collectors.joining(", ")))
                .totalPrice(totalPriceOfSeatBooked(foundTrainClass.getPrice(), requestedSeatNumbers))
                .bookingStatus(BookingStatus.CONFIRMED)
                .bookingDate(LocalDateTime.now())
                .schedule(foundSchedule)
                .trainClass(foundTrainClass)
                .build();

        return bookingRepository.save(newBooking); // Persist booking
    }





                private int totalPriceOfSeatBooked(double price, List<Integer> selectedSeatNumbers){
                    return (int) (price * selectedSeatNumbers.size());

                }

    public List<Integer> getAvailableSeats(Long trainClassId) {
        TrainClass trainClass = trainClassService.findById(trainClassId)
                .orElseThrow(() -> new TrainClassCannotBeFoundException("TrainClass not found"));

        return trainClass.getSeats().stream()
                .filter(seat -> seat.getSeatStatus() == SeatStatus.AVAILABLE)
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
    }

}
