package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.DoctorAdvice;

import javax.persistence.EntityManager;

public class AdviceRepository extends Repository<DoctorAdvice, Integer>{

    public AdviceRepository(EntityManager entityManager) { super(entityManager); }

    @Override
    public Class<DoctorAdvice> getEntityClass() { return DoctorAdvice.class; }

    @Override
    public String getClassName() { return DoctorAdvice.class.getName(); }

}
