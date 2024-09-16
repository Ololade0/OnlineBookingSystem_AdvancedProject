package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dao.model.TrainClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TrainServiceImplTest {
    @Autowired
    private TrainService trainService;
    Train newTrain;

    @BeforeEach
    void setUp() {
        List<TrainClass> trainClassList = List.of(
                new TrainClass("First Class"),
                new TrainClass("Second Class"),
                new TrainClass("Third Class")
        );

        Train addTrain = Train.builder()
                .trainType("coauch 5")
                .trainName("Moniya Trian")
                .trainClasses(trainClassList)
                .build();
        newTrain = trainService.saveTrain(addTrain);
    }

    @AfterEach
    void tearDown() {
    }

                @Test
                void saveTrain() {
                    List<TrainClass> trainClassList = List.of(
                            new TrainClass("First Class"),
                            new TrainClass("Second Class"),
                            new TrainClass("Third Class")
                    );

                    Train addTrain = Train.builder()
                            .trainType("coauch 5")
                            .trainName("Moniya Trian")
                            .trainClasses(trainClassList)
                            .build();
                    trainService.saveTrain(addTrain);
                   assertEquals("coauch 5", addTrain.getTrainType());
                    assertNotNull(addTrain.getTrainClasses());

                   }

                @Test
                void findTrain() {

                Train foundTrain = trainService.getTrain(newTrain.getTrainId());
                   assertNotNull(foundTrain.getTrainId());
                           assertThat(foundTrain.getTrainId()).isEqualTo(newTrain.getTrainId());

                }

                @Test
                void editTrain(){
                    List<TrainClass> trainClassList = List.of(
                            new TrainClass("Standard Class"),
                            new TrainClass("Economy Class"),
                            new TrainClass("Business Class")
                    );
                    Train updateTrain = Train.builder()
                            .trainId(newTrain.getTrainId())
                            .trainType("coauch 10")
                            .trainName("Agege Trian")
                            .trainClasses(trainClassList)
                            .build();
                   String updatedTrain = trainService.updateTrain(updateTrain, updateTrain.getTrainId());
                    assertEquals("Train successfully updated", updatedTrain );
               }

                @Test
                void findCreatedSchedule() {
                }
            }