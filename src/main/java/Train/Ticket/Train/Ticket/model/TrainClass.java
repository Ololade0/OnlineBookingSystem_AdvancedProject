package Train.Ticket.Train.Ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "trainclass")

public class TrainClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private double price;
    private double availableSeat;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    @JsonIgnore
    private Train trainclass;


}
