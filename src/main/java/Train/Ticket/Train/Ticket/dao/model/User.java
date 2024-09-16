package Train.Ticket.Train.Ticket.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phoneNumber;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Role> roleSet = new HashSet<>();


}
