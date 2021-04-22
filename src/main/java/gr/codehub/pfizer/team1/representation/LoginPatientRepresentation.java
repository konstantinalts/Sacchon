package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginPatientRepresentation {

    private String username;
    private String password;

    public LoginPatientRepresentation(Patient patient){
        if (patient != null) {
            username = patient.getUsername();
            password = patient.getPassword();
        }
    }
}
