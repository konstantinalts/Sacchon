package gr.codehub.pfizer.team1.model;

import gr.codehub.pfizer.team1.enums.Medication;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class DoctorAdvice {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "medication", nullable = true)
    private Medication medication;

    @Column(name = "dosage", nullable = true)
    private float dosage;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

}