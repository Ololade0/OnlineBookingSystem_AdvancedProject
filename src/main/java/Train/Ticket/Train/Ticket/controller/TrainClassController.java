package Train.Ticket.Train.Ticket.controller;


import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import Train.Ticket.Train.Ticket.dto.request.AddTrainClassRequest;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;
import Train.Ticket.Train.Ticket.service.TrainClassService;
import Train.Ticket.Train.Ticket.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/trainsclass")
public class TrainClassController {

    @Autowired
    private TrainClassService trainClassService;


//
//    @PostMapping("/add")
//    public Map<String, TrainClass> addNewTrainClasses(@RequestBody Map<String, Map<String, Object>> trainClassRequest) {
//        return trainClassService.saveNewTrainClasses(trainClassRequest);
//    }
}



