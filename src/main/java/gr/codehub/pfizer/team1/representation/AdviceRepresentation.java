package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.enums.Medication;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@NoArgsConstructor
public class AdviceRepresentation {

    private int id;
    private Date date;
    private Medication medication;
    private float dosage;
    private String description;
    private Doctor doctor;
    private Patient patient;


    private String uri;

    public AdviceRepresentation(DoctorAdvice doctorAdvice) {
        if (doctorAdvice != null) {
            date = doctorAdvice.getDate();
//            medication = doctorAdvice.getMedication();
            dosage = doctorAdvice.getDosage();
            description = doctorAdvice.getDescription();
//            doctor = doctorAdvice.getDoctor();
//            patient = doctorAdvice.getPatient();

            uri =  "http://localhost:9000/v1/doctorAdvice/" + doctorAdvice.getId();
        }

    }

    public DoctorAdvice createDoctorAdvice() {
        DoctorAdvice doctorAdvice = new DoctorAdvice();
        doctorAdvice.setDate(date);
        doctorAdvice.setMedication(medication);
        doctorAdvice.setDosage(dosage);
        doctorAdvice.setDescription(description);
        doctorAdvice.setDoctor(doctor);
        doctorAdvice.setPatient(patient);

        return doctorAdvice;
    }

}
