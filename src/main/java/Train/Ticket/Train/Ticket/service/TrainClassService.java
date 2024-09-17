package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.TrainClass;

import java.util.Optional;

public interface TrainClassService {
    Optional<TrainClass> findById(Long trainClassId);

    TrainClass savenewTrainClass(TrainClass trainClass);
}
