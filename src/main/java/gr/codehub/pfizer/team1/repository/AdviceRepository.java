package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class AdviceRepository extends Repository<DoctorAdvice, Integer>{

    private EntityManager entityManager;

    public AdviceRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<DoctorAdvice> getEntityClass() { return DoctorAdvice.class; }

    @Override
    public String getClassName() { return DoctorAdvice.class.getName(); }

    public List<Doctor> getDoctors(int doctorId){
        return entityManager.createQuery("SELECT p FROM Doctor pm inner join pm.doctorAdvices p WHERE pm.id = : doctorId", Doctor.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    public List<Patient> getPatients(int patientId){
        return entityManager.createQuery("SELECT p FROM Patient pm inner join pm.doctorAdvices p WHERE pm.id = : patientId", Patient.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }





}
