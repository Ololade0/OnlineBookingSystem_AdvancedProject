package Train.Ticket.Train.Ticket.service;

import train.booking.trainbooking.dao.model.TrainClass;

public interface SeatService {
    void generateSeatsForTrainClass(TrainClass trainClass, int startSeatNumber, int endSeatNumber);
}
