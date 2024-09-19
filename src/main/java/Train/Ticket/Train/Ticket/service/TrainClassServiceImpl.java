package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.dao.repository.TrainClassRepository;
import Train.Ticket.Train.Ticket.dto.request.AddTrainClassRequest;
import Train.Ticket.Train.Ticket.exception.TrainClassCannotBeFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainClassServiceImpl  implements TrainClassService{

    @Autowired
    private TrainClassRepository trainClassRepository;
    @Override
    public Optional<TrainClass> findById(Long trainClassId) {
        Optional<TrainClass> foundTrainClass =  trainClassRepository.findById(trainClassId);
        if(foundTrainClass.isPresent()){
            return foundTrainClass;
        }
        throw new TrainClassCannotBeFoundException("Train Class cannot be found");
    }

public Map<String, TrainClass> saveNewTrainClasses(Map<String, Map<String, Object>> trainClassRequest) {
    Map<String, TrainClass> savedTrainClasses = new HashMap<>();

    for (Map.Entry<String, Map<String, Object>> entry : trainClassRequest.entrySet()) {
        String className = entry.getKey();
        Map<String, Object> details = entry.getValue();
        Double price = (Double) details.get("price");
        Integer availableSeat = (Integer) details.get("availableSeat");

        TrainClass newTrainClass = TrainClass.builder()
                .className(className)
                .price(price)
                .availableSeat(availableSeat)
                .seats(new ArrayList<>())
                .build();

        savedTrainClasses.put(className, trainClassRepository.save(newTrainClass));
    }

    return savedTrainClasses;
}
}


