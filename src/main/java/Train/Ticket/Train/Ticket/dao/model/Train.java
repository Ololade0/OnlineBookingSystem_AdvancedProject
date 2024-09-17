package Train.Ticket.Train.Ticket.dao.model;

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


    @OneToMany(mappedBy = "trainclass", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TrainClass> trainClasses;

    public void addTrainClass(TrainClass trainClass) {
        trainClasses.add(trainClass);
        trainClass.setTrainclass(this);
    }
    public void removeTrainClass(TrainClass trainClass) {
        trainClasses.remove(trainClass);
        trainClass.setTrainclass(null);
    }
}






