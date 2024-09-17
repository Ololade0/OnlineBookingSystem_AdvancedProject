package Train.Ticket.Train.Ticket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingRequest {
    private Long trainClassId;
   private Long scheduleId;
    List<Integer> selectedSeatNumber;


}
