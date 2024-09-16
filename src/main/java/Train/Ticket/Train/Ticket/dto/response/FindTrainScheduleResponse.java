package Train.Ticket.Train.Ticket.dto.response;

import Train.Ticket.Train.Ticket.dao.model.Schedule;
import Train.Ticket.Train.Ticket.dao.model.Train;
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
