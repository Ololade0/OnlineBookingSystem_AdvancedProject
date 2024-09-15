package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.model.Station;
import Train.Ticket.Train.Ticket.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService{
    private final StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
        Station newStation = Station.builder()
                .stationName(station.getStationName())
                .build();
                return stationRepository.save(newStation);
    }


}
