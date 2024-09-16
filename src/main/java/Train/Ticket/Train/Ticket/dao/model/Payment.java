package Train.Ticket.Train.Ticket.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long transactionId;


    private BigDecimal amount;
    private LocalDateTime paymentDateTime;
    private BigDecimal refundAmount;
    private LocalDateTime refundDateTime;


    @Enumerated(EnumType.STRING)
    private PaymentMethode paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;




}
