package Train.Ticket.Train.Ticket.controller;

import Train.Ticket.Train.Ticket.model.Station;
import Train.Ticket.Train.Ticket.model.Train;
import Train.Ticket.Train.Ticket.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class StationController {
    private final StationService stationService;

    @PostMapping("/createstation")
    public ResponseEntity<?> createStation(@RequestBody Station station) {
        Station createStation = stationService.createStation(station);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStation);
    }
}
