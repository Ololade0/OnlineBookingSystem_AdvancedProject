package Train.Ticket.Train.Ticket.service;

import Train.Ticket.Train.Ticket.dao.model.Booking;
import Train.Ticket.Train.Ticket.dao.model.RoleStatus;
import Train.Ticket.Train.Ticket.dao.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private  UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signUpNewUser() {
//        List<Booking> bookingList = List.of();
             User signUpRequest = User.builder()
                .firstName("Ololade")
                .lastName("Oluwatosin")
                .email("adesuyiololad@gmail.com")
                .password("1234")
                .phoneNumber("08109093828")
//                .b(bookingList)
                .build();
        userService.signUpNewUser(signUpRequest, RoleStatus.ADMIN_ROLE);
        assertEquals("Ololade", signUpRequest.getFirstName());
    }


    @Test
    void findTrainSchdule() {
    }
}