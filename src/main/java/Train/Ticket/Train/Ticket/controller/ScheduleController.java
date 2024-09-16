package Train.Ticket.Train.Ticket.controller;



import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private final ScheduleService ScheduleService;
    @PostMapping("create")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule createScheduleRequest) {
        Schedule createdSchedule = ScheduleService.createSchedule(createScheduleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

}