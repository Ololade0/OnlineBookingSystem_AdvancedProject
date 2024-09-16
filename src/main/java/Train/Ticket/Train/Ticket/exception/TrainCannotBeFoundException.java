package Train.Ticket.Train.Ticket.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class TrainCannotBeFoundException extends RuntimeException {

   public TrainCannotBeFoundException(String message){
       super(message);
   }

}
