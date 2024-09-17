package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.repository.ScheduleRepository;
import Train.Ticket.Train.Ticket.exception.ScheduleCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule createSchedule(Schedule scheduleRequest) {

        Schedule schedule = Schedule.builder()
                .createdAt(LocalDateTime.now())
                .arrivalStation(scheduleRequest.getArrivalStation())
                .departureStation(scheduleRequest.getDepartureStation())
                .departureTime(scheduleRequest.getDepartureTime())
                .arrivalTime(scheduleRequest.getArrivalTime())
                .duration(scheduleRequest.getDuration())
                .build();
                 return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<Schedule> findSchedule(Long schedule) {
        Optional<Schedule> foundSchedule =  scheduleRepository.findById(schedule);
        if(foundSchedule.isPresent()){
            return foundSchedule;
        }
        throw new RuntimeException("Schedule cannot be found");
    }

    @Override
    public Schedule updateSchedule(Schedule createdSchedule, Long scheduleId) throws ScheduleCannotBeFoundException {
      Optional<Schedule> foundSchedule =  scheduleRepository.findById(scheduleId);
      if(foundSchedule.isPresent()){
          foundSchedule.get().setArrivalStation(createdSchedule.getArrivalStation());
          foundSchedule.get().setUpdatedAt(LocalDateTime.now());
          foundSchedule.get().setDepartureStation(createdSchedule.getDepartureStation());
          foundSchedule.get().setDuration(createdSchedule.getDuration());
          foundSchedule.get().setTrainSchedule(createdSchedule.getTrainSchedule());
          foundSchedule.get().setArrivalTime(createdSchedule.getArrivalTime());
          foundSchedule.get().setDepartureTime(createdSchedule.getDepartureTime());
          return scheduleRepository.save(foundSchedule.get());
      }
        throw new ScheduleCannotBeFoundException("Schedule cannot Be found");
    }

}
