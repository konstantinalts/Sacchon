package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.Patient;

import javax.persistence.EntityManager;

public class PatientRepository extends Repository<Patient, Integer>{

    public PatientRepository(EntityManager entityManager) { super(entityManager); }

    @Override
    public Class<Patient> getEntityClass() { return Patient.class; }

    @Override
    public String getClassName() { return Patient.class.getName(); }


}
