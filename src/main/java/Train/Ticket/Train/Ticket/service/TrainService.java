package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.model.Schedule;
import Train.Ticket.Train.Ticket.model.Train;
import Train.Ticket.Train.Ticket.service.response.FindTrainScheduleResponse;

import java.util.Optional;

public interface TrainService {
    Train saveTrain(Train train);
    Train createScheduleForTrain(Long trainId, Schedule schedule);
   FindTrainScheduleResponse findCreatedSchedule(Long trainId, Long scheduleId);


}

