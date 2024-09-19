package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;

public interface TrainService {
    Train saveTrain(Train train);
    Train createScheduleForTrain(Long trainId, Schedule schedule);
   FindTrainScheduleResponse findCreatedSchedule(Long trainId, Long scheduleId);


    Train getTrain(Long trainId);

//    Train saveTrainWithClasses(Train train);

//   String updateTrain(Train updateTrain, Long trainId);
}

