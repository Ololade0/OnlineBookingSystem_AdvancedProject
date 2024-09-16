package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.dao.repository.TrainRepository;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;
import Train.Ticket.Train.Ticket.exception.TrainCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements  TrainService {
    private final TrainRepository trainRepository;
    private final ScheduleService scheduleService;


    public Train saveTrain(Train train) {
        int totalSeat = 0;
        for (TrainClass trainClass : train.getTrainClasses()) {
            trainClass.setTrainclass(train);
            totalSeat += trainClass.getAvailableSeat();
        }
        train.setTrainTotalSeat(totalSeat);
        return trainRepository.save(train);
    }

    @Override
    public Train createScheduleForTrain(Long trainId, Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        Train foundTrain = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train with ID " + trainId + " cannot be found"));
        foundTrain.getScheduleList().add(createdSchedule);
        createdSchedule.setTrainSchedule(foundTrain);
        return trainRepository.save(foundTrain);
    }


    @Override
    public FindTrainScheduleResponse findCreatedSchedule(Long trainId, Long scheduleId) {
        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
        Optional<Schedule> foundSchedule = scheduleService.findSchedule(scheduleId);
        if (foundTrain.isPresent()) {
            if (foundSchedule.isPresent()) {
                return new FindTrainScheduleResponse(foundTrain.get(), foundSchedule.get());
            }
            throw new RuntimeException("Schedule cannot be found");

        }

        throw new RuntimeException("Train cannot be found");
    }

    @Override
    public Train getTrain(Long trainId) {
        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
        if (foundTrain.isPresent()) {
            return foundTrain.get();
        }
        throw new TrainCannotBeFoundException("Train Cannot be found");
    }

    @Override
    public String updateTrain(Train updateTrain, Long trainId) {
        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
        if (foundTrain.isPresent()) {
            Train trainToUpdate = foundTrain.get();
            trainToUpdate.setTrainName(updateTrain.getTrainName());
            trainToUpdate.setTrainType(updateTrain.getTrainType());

            List<TrainClass> trainClasses = updateTrain.getTrainClasses();
            for (TrainClass trainClass : trainClasses) {
                trainClass.setTrainclass(trainToUpdate);

            }

            trainToUpdate.setTrainClasses(trainClasses);
            trainRepository.save(trainToUpdate);

            return "Train successfully updated";
        } else {
            throw new TrainCannotBeFoundException("Train cannot Be found");
        }
    }


}


