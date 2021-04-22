package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDoctorRepresentation {

    private String username;
    private String password;

    public LoginDoctorRepresentation(Doctor doctor){
        if (doctor != null) {
            username = doctor.getUsername();
            password = doctor.getPassword();
        }
    }

    public Doctor createLogin(){
        Doctor doctor = new Doctor();
        doctor.setUsername(username);
        doctor.setPassword(password);
        return doctor;
    }
}
