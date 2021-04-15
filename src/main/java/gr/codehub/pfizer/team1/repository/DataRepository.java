package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Patient> getData(int patientId){
        return entityManager.createQuery("SELECT p FROM Patient pm inner join pm.mediDataRepos p WHERE pm.id = : patientId", Patient.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

}
