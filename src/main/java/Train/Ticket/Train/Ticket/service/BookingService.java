package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Booking;
import Train.Ticket.Train.Ticket.dto.request.BookingRequest;

import java.util.List;

public interface BookingService {
    public Booking createBooking(BookingRequest bookingRequest);
    public List<Integer> getAvailableSeats(Long trainClassId);
}
