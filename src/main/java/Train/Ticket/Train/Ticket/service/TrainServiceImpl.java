package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.model.Schedule;
import Train.Ticket.Train.Ticket.model.Train;
import Train.Ticket.Train.Ticket.model.TrainClass;
import Train.Ticket.Train.Ticket.repository.TrainRepository;
import Train.Ticket.Train.Ticket.service.response.FindTrainScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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


}
