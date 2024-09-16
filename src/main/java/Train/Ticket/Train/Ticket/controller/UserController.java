package Train.Ticket.Train.Ticket.controller;

import Train.Ticket.Train.Ticket.dao.model.RoleStatus;
import Train.Ticket.Train.Ticket.dao.model.User;
import Train.Ticket.Train.Ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User signUpUser, RoleStatus roleStatus){
        User registeredUser = userService.signUpNewUser(signUpUser, roleStatus);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
