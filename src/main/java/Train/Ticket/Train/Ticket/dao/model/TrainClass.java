package Train.Ticket.Train.Ticket.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@Entity(name = "trainclass")
@AllArgsConstructor
@NoArgsConstructor
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


    public TrainClass(String className) {
        this.className = className;
    }
}
