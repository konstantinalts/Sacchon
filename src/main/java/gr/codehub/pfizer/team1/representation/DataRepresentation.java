package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
public class DataRepresentation {

    private int id;
    private Date date;
    private Time time;
    private int glucoseLevel;
    private float carbIntake;
    private Patient patient;

    private String uri;


    public DataRepresentation(MediDataRepo mediDataRepo){
        if (mediDataRepo != null){
            date = mediDataRepo.getDate();
            time = mediDataRepo.getTime();
            glucoseLevel = mediDataRepo.getGlucoseLevel();
            carbIntake = mediDataRepo.getCarbIntake();
            patient = mediDataRepo.getPatient();

            uri = "http://localhost:9000/v1/mediData/" + mediDataRepo.getId();
        }
    }


    public MediDataRepo createData()  {
        MediDataRepo mediDataRepo = new MediDataRepo();
        mediDataRepo.setDate(date);
        mediDataRepo.setTime(time);
        mediDataRepo.setGlucoseLevel(glucoseLevel);
        mediDataRepo.setCarbIntake(carbIntake);
        mediDataRepo.setPatient(patient);

        return mediDataRepo;
    }



}
