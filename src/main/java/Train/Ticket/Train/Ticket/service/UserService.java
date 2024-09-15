package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.model.RoleStatus;
import Train.Ticket.Train.Ticket.model.User;
public interface UserService {
    User signUpNewUser(User signUpRequest, RoleStatus roleStatus);

}
