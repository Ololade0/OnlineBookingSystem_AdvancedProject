package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.model.Schedule;

import java.util.Optional;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Optional<Schedule> findSchedule(Long schedule);
}
