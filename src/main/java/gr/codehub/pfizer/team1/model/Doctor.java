package gr.codehub.pfizer.team1.model;

import gr.codehub.pfizer.team1.enums.ChiefDoctor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Doctor extends User{


    @Enumerated(EnumType.STRING)
    @Column(name = "chiefDoctor", nullable = true)
    private ChiefDoctor chiefDoctor;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DoctorAdvice> doctorAdvices;

}
