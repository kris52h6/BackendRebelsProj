package dat3.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    String name;
    String address;
    String email;

    String teams;

    @OneToMany
    List<Referee> referees;

    public Club(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }



}
