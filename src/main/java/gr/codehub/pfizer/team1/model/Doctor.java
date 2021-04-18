package gr.codehub.pfizer.team1.model;

import gr.codehub.pfizer.team1.enums.ChiefDoctor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Doctor{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "lname", nullable = false)
    private String lname;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "chiefDoctor", nullable = true)
    private ChiefDoctor chiefDoctor;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoctorAdvice> doctorAdvices;

}
