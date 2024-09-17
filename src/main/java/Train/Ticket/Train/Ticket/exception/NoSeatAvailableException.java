package Train.Ticket.Train.Ticket.exception;

public class NoSeatAvailableException extends RuntimeException {
    public NoSeatAvailableException(String message) {
        super(message);
    }
}
