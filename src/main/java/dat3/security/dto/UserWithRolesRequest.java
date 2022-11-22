package dat3.security.dto;



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
    String position;

    String license;

    String bankInformation;

    public UserWithRolesRequest(String username, String password, String email,
                              String position, String license, String bankInformation, boolean isReferee) {
        this.username = username;
        this.password = password;
        this.email = email;
        if(isReferee){

            this.position = position;
            this.license = license;
            this.bankInformation = bankInformation;
        }

    }


}