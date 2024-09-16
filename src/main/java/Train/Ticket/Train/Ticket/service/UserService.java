package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.RoleStatus;
import Train.Ticket.Train.Ticket.dao.model.User;
public interface UserService {
    User signUpNewUser(User signUpRequest, RoleStatus roleStatus);

}
