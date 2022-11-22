package dat3.backend.entity;

import dat3.security.entity.UserWithRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referee extends UserWithRoles {

    @Column(name = "kampe")
    String matches;


    String position;

    String license;

    @Column(name = "bankOplysninger")
    String bankInformation;

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
