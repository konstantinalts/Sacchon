package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.Doctor;

import javax.persistence.EntityManager;

public class DoctorRepository extends Repository<Doctor, Integer>{

    public DoctorRepository(EntityManager entityManager) { super(entityManager); }

    @Override
    public Class<Doctor> getEntityClass() { return Doctor.class; }

    @Override
    public String getClassName() { return Doctor.class.getName(); }


}
