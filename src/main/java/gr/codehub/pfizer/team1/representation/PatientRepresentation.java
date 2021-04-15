package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class PatientRepresentation {

    private int id;

    private String fname;
    private String lname;
    private Date birthdate;
    private String address;
    private long telephone;
    private String email;
    private String username;
    private String password;
    private Doctor doctor;
    private List<MediDataRepo> mediDataRepos;
    private List<DoctorAdvice> doctorAdvices;


    private String uri;

    public PatientRepresentation(Patient patient) {
        if (patient != null) {
            fname = patient.getFname();
            lname = patient.getLname();
            birthdate = patient.getBirthdate();
            address = patient.getAddress();
            telephone = patient.getTelephone();
            email = patient.getEmail();
            username = patient.getUsername();
            password = patient.getPassword();
//            doctor = patient.getDoctor();
//            mediDataRepos = patient.getMediDataRepos();
//            doctorAdvices = patient.getDoctorAdvices();

            uri =  "http://localhost:9000/v1/patient/" + patient.getId();
        }

    }

    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setFname(fname);
        patient.setLname(lname);
        patient.setBirthdate(birthdate);
        patient.setAddress(address);
        patient.setTelephone(telephone);
        patient.setEmail(email);
        patient.setUsername(username);
        patient.setPassword(password);
        patient.setDoctor(doctor);
        patient.setMediDataRepos(mediDataRepos);
        patient.setDoctorAdvices(doctorAdvices);

        return patient;
    }



}