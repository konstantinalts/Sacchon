package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.MediDataRepo;

import javax.persistence.EntityManager;

public class DataRepository extends Repository<MediDataRepo, Integer>{

    private EntityManager entityManager;
    public DataRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<MediDataRepo> getEntityClass() { return MediDataRepo.class; }

    @Override
    public String getClassName() { return MediDataRepo.class.getName(); }



}
