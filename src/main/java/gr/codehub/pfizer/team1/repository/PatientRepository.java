package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientRepository extends Repository<Patient, Integer>{

    private EntityManager entityManager;

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Patient> getEntityClass() { return Patient.class; }

    @Override
    public String getClassName() { return Patient.class.getName(); }


    public Patient getByLastName(String lname){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.lname = :lname", Patient.class)
                .setParameter("lname",lname)
                .getSingleResult();
    }




}
