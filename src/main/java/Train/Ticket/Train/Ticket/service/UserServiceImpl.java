package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Role;
import Train.Ticket.Train.Ticket.dao.model.RoleStatus;
import Train.Ticket.Train.Ticket.dao.model.Train;
import Train.Ticket.Train.Ticket.dao.model.User;
import Train.Ticket.Train.Ticket.dao.repository.UserRepository;

import Train.Ticket.Train.Ticket.exception.UserCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final TrainService trainService;


    @Override
    public User signUpNewUser(User signUpRequest, RoleStatus roleStatus){
        User user = User.builder()
        .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .password(signUpRequest.getPassword())
                .roleSet(new HashSet<>())
                .build();
        user.getRoleSet().add(new Role(roleStatus));
       return userRepository.save(user);

    }

    public Train findTrainSchdule(Long userId, Long scheduleId, Train train) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isPresent()){
            trainService.findCreatedSchedule(train.getTrainId(), scheduleId);

        }
        throw new UserCannotBeFoundException("User with name " + foundUser.get().getFirstName() + "cannot be found");
    }

}
