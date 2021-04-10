package gr.codehub.pfizer.team1.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class MediDataRepo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private Time time;

    @Column(name = "glucoseLevel", nullable = false)
    private int glucoseLevel;

    @Column(name = "carbIntake", nullable = false)
    private float carbIntake;

    @Column(name = "patientId", nullable = false)
    private int patientId;
}
