package Train.Ticket.Train.Ticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Setter
@ToString
@Getter
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private RoleStatus roleStatus;
    public Role(RoleStatus roleStatus){
        this.roleStatus =roleStatus ;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleStatus=" + roleStatus +
                '}';
    }

}
