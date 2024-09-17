package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Seat;
import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.dao.repository.TrainClassRepository;
import Train.Ticket.Train.Ticket.exception.TrainCannotBeFoundException;
import Train.Ticket.Train.Ticket.exception.TrainClassCannotBeFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TrainClass savenewTrainClass(TrainClass trainClass) {
        List<Seat> seatList = List.of();
        TrainClass newTrainClass = TrainClass.builder()
                .className(trainClass.getClassName())
                .price(trainClass.getPrice())
                .availableSeat(trainClass.getAvailableSeat())
                .seats(seatList)
                .build();
                return trainClassRepository.save(newTrainClass);
    }
}
