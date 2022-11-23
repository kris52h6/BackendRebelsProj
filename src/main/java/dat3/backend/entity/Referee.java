package dat3.backend.entity;

import dat3.security.entity.UserWithRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referee extends UserWithRoles {

    @Column(name = "kampe")
    @OneToMany
    List<Match> matches;

    String position;

    String license;

    @Column(name = "bankOplysninger")
    String bankInformation;

    @ManyToOne
    Club club;

    public Referee(String username, String password, String email,
                    String position, String license, String bankInformation )
    {
        super(username, password, email);
        this.position = position;
        this.license = license;
        this.bankInformation = bankInformation;

    }

    public Referee(String username, String password, String email){
        super(username,password, email);
    }
}
