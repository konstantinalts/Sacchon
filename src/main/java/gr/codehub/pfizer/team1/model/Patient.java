package gr.codehub.pfizer.team1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Patient{

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctorId;


    @OneToMany(mappedBy = "patientId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MediDataRepo> mediDataRepos;


    @OneToMany(mappedBy = "patientId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoctorAdvice> doctorAdvices;

}
