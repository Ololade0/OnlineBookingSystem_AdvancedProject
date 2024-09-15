package Train.Ticket.Train.Ticket.service.response;

import Train.Ticket.Train.Ticket.model.Schedule;
import Train.Ticket.Train.Ticket.model.Train;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindTrainScheduleResponse {
    private Train train;
    private Schedule schedule;
}
