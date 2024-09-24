package Train.Ticket.Train.Ticket.dao.model;

import Train.Ticket.Train.Ticket.exception.NoSeatAvailableException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
@Entity(name = "trainclass")
@AllArgsConstructor
@NoArgsConstructor
public class TrainClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
     private double price;
    private int startSeatNumber;
    private int endSeatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    @JsonIgnore
    private Train train;

    @OneToMany(mappedBy = "trainClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats = new ArrayList<>();

    public TrainClass(String className) {
        this.className = className;
    }


    public void setTrain(Train train) {
        this.train = train;
    }
}
