package Train.Ticket.Train.Ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

        private String stationName;

        @ManyToOne
        @JoinColumn(name = "schedule_id")
        private Schedule schedule;

        // Getters and setters
}
