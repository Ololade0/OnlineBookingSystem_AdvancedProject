package Train.Ticket.Train.Ticket.controller;


import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.service.TrainService;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;





    @PostMapping("/savetrain")
        public ResponseEntity<?> saveTrain(@RequestBody Train train){
           Train savedTrain = trainService.saveTrain(train);
           return new ResponseEntity<>(savedTrain, HttpStatus.CREATED);
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



