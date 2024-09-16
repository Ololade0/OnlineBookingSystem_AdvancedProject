package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.exception.ScheduleCannotBeFoundException;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScheduleServiceImplTest {
    Schedule createdSchedule;

    @Autowired
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        Schedule createSchedule = Schedule.builder()
                .departureStation("Moniya Station")
                .departureTime(LocalDateTime.of(2024, 9, 16, 15, 30, 0))
                .arrivalStation("Agbado Station")
                .arrivalTime(LocalDateTime.of(2024, 9, 16, 15, 30, 0))
                .duration("3 hrs")
                .build();
       createdSchedule = scheduleService.createSchedule(createSchedule);
    }

    @Test
    void createSchedule() {
        Schedule createSchedule = Schedule.builder()
                .departureStation("Moniya Station")
                .departureTime(LocalDateTime.of(2024, 9, 16, 15, 30, 0))
                .arrivalStation("Agbado Station")
                .arrivalTime(LocalDateTime.of(2024, 9, 16, 15, 30, 0))
                .duration("3 hrs")
                .build();
        scheduleService.createSchedule(createSchedule);
        assertEquals("Moniya Station", createSchedule.getDepartureStation());
    }

    @Test
    void findSchedule() {
     Optional<Schedule> foundSchdeule =    scheduleService.findSchedule(createdSchedule.getScheduleId());
        Assertions.assertThat(foundSchdeule.get().getScheduleId()).isEqualTo(createdSchedule.getScheduleId());
   }

  @Test
    void updateSchedule() throws ScheduleCannotBeFoundException {
      Schedule createSchedule = Schedule.builder()
              .scheduleId(createdSchedule.getScheduleId())
              .departureStation("Agege Station")
              .departureTime(LocalDateTime.of(2024, 9, 16, 19, 30, 0))
              .arrivalStation("Iwo Station")
              .arrivalTime(LocalDateTime.of(2024, 9, 16, 12, 30, 0))
              .duration("6 hrs")
                      .build();
      Schedule updatedSchedule = scheduleService.updateSchedule(createSchedule, createSchedule.getScheduleId());
        assertEquals("Agege Station", updatedSchedule.getDepartureStation());
         assertEquals("Iwo Station", updatedSchedule.getArrivalStation());
  }
}