package Train.Ticket.Train.Ticket.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "trains")

public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;
    private String trainName;
    private Integer trainTotalSeat;
    private String trainType;
    @OneToMany(mappedBy = "trainSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TrainClass> trainClasses = new HashSet<>();

    public void addTrainClass(TrainClass trainClass) {
        trainClasses.add(trainClass);
        trainClass.setTrain(this);
    }
    public void removeTrainClass(TrainClass trainClass) {
        trainClasses.remove(trainClass);
        trainClass.setTrain(null);
    }
}






