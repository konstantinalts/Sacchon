package gr.codehub.pfizer.team1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.codehub.pfizer.team1.enums.Medication;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
public class DoctorAdvice {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "dosage", nullable = true)
    private float dosage;

    @Column(name = "description", nullable = true)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctorId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patientId;


}