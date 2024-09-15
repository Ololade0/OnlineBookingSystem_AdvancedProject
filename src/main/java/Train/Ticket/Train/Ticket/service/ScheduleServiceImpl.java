package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.model.Schedule;
import Train.Ticket.Train.Ticket.model.Station;
import Train.Ticket.Train.Ticket.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule createSchedule(Schedule scheduleRequest) {
        Schedule schedule = Schedule.builder()
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
}
