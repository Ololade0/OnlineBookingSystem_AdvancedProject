package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.dto.request.AddTrainClassRequest;

import java.util.Map;
import java.util.Optional;

public interface TrainClassService {
    Optional<TrainClass> findById(Long trainClassId);

    Map<String, TrainClass> saveNewTrainClasses(Map<String, Map<String, Object>> trainClassRequest);

}
