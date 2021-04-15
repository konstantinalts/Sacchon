package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.enums.ChiefDoctor;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class DoctorRepresentation {

    private int id;

    private String fname;
    private String lname;
    private Date birthdate;
    private String address;
    private long telephone;
    private String email;
    private String username;
    private String password;
    private String chiefDoctor;
    private List<Patient> patients;
    private List<DoctorAdvice> doctorAdvices;


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
            chiefDoctor = String.valueOf(doctor.getChiefDoctor());
            patients = doctor.getPatients();
            doctorAdvices = doctor.getDoctorAdvices();

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
        doctor.setChiefDoctor(ChiefDoctor.valueOf(chiefDoctor));
        doctor.setPatients(patients);
        doctor.setDoctorAdvices(doctorAdvices);

        return doctor;
    }

}