package Train.Ticket.Train.Ticket.dto.request;

import Train.Ticket.Train.Ticket.dao.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddTrainClassRequest {

    private String className;
    private double price;
    private int availableSeat;
    private List<Seat> seats = new ArrayList<>();

}
