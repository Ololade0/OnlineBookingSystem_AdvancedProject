package Train.Ticket.Train.Ticket.controller;

import Train.Ticket.Train.Ticket.dao.model.Booking;
import Train.Ticket.Train.Ticket.dto.request.BookingRequest;
import Train.Ticket.Train.Ticket.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {

        Booking booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/available-seats/{trainClassId}")
    public ResponseEntity<List<Integer>> getAvailableSeats(@PathVariable Long trainClassId) {
        List<Integer> availableSeats = bookingService.getAvailableSeats(trainClassId);
        return ResponseEntity.ok(availableSeats);
    }
}
