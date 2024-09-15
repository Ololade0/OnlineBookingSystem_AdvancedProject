package Train.Ticket.Train.Ticket.controller;


import Train.Ticket.Train.Ticket.model.Schedule;
import Train.Ticket.Train.Ticket.model.Train;
import Train.Ticket.Train.Ticket.service.TrainService;
import Train.Ticket.Train.Ticket.service.response.FindTrainScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;


        @PostMapping("/create")
        public ResponseEntity<Train> createTrain(@RequestBody Train train) {
            Train savedTrain = trainService.saveTrain(train);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTrain);
        }
            @PostMapping("/{trainId}/schedule")
            public ResponseEntity<Train> createScheduleForTrain(@PathVariable Long trainId, @RequestBody Schedule schedule) {
                Train savedTrain = trainService.createScheduleForTrain(trainId, schedule);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedTrain);
            }

            @GetMapping("/{trainId}/{scheduleId}/findschedule")
            public ResponseEntity<?> FindTrain(@PathVariable Long trainId, @PathVariable Long scheduleId) {
                FindTrainScheduleResponse foundTrain = trainService.findCreatedSchedule(trainId, scheduleId);
                return ResponseEntity.status(HttpStatus.CREATED).body(foundTrain);
            }

    }



