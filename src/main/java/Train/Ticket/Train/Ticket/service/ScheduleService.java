package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.exception.ScheduleCannotBeFoundException;

import java.util.Optional;

public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);
    Optional<Schedule> findSchedule(Long schedule);

    Schedule updateSchedule(Schedule createdSchedule, Long scheduleId) throws ScheduleCannotBeFoundException;
}
