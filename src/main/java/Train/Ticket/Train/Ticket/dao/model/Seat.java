package Train.Ticket.Train.Ticket.dao.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "train_class_id", nullable = false)
    private TrainClass trainClass;

    public Seat(int seatNumber, SeatStatus available, TrainClass trainClass) {
        this.seatNumber = seatNumber;
        this.seatStatus = available;
        this.trainClass = trainClass;
    }
}
