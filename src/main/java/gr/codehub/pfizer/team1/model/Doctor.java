package gr.codehub.pfizer.team1.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Doctor {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fname", nullable = false)
    private int fname;

    @Column(name = "lname", nullable = false)
    private int lname;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telephone", nullable = false)
    private long telephone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "removedAt", nullable = true)
    private Date removedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "chiefDoctor", nullable = true)
    private String chiefDoctor;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctorAdvice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoctorAdvice> doctorAdvices;

}
