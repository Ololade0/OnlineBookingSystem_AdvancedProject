package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.model.Role;
import Train.Ticket.Train.Ticket.model.RoleStatus;
import Train.Ticket.Train.Ticket.model.Train;
import Train.Ticket.Train.Ticket.model.User;
import Train.Ticket.Train.Ticket.repository.UserRepository;

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

    public Train findTrainSchdule(Long userId, Long scheduleId, Train train) {
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isPresent()){
            trainService.findCreatedSchedule(train.getTrainId(), scheduleId);

        }
        return null;
    }

}
