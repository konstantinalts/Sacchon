package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class DoctorRepresentation {

    private int id;

    private String fname;
    private String lname;
    private String birthdate;
    private String address;
    private String telephone;
    private String email;
    private String username;
    private String password;
    private String role;
    private String chiefDoctor;


    private String uri;

    public DoctorRepresentation(Doctor doctor) {
        if (doctor != null) {
            fname = doctor.getFname();
            lname = doctor.getLname();
            birthdate = doctor.getBirthdate();
            address = doctor.getAddress();
            telephone = doctor.getTelephone();
            email = doctor.getEmail();
            username = doctor.getUsername();
            password = doctor.getPassword();
            role = doctor.getRole();
            chiefDoctor = String.valueOf(doctor.getChiefDoctor());

            uri =  "http://localhost:9000/v1/doctor/" + doctor.getId();
        }

    }

    public Doctor createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFname(fname);
        doctor.setLname(lname);
        doctor.setBirthdate(birthdate);
        doctor.setAddress(address);
        doctor.setTelephone(telephone);
        doctor.setEmail(email);
        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setRole(role);
        doctor.setChiefDoctor(chiefDoctor);

        return doctor;
    }

}