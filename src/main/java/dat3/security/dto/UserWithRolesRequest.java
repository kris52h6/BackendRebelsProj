package dat3.security.dto;



import dat3.backend.entity.License;
import dat3.security.entity.UserWithRoles;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserWithRolesRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;

    @NonNull
    String firstname;

    @NonNull
    String lastname;

    String position;

    License license;

    String bankInformation;

    public UserWithRolesRequest(String username, String password, String email, String firstname,
                              String lastname, String position, License license, String bankInformation, boolean isReferee) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        if(isReferee){

            this.position = position;
            this.license = license;
            this.bankInformation = bankInformation;
        }

    }

    public UserWithRolesRequest(UserWithRoles user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }


}