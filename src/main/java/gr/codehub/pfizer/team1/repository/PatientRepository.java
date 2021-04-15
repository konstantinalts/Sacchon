package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.Doctor;
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

    public Patient getByUsername(String username){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.username = :username", Patient.class)
                .setParameter("username",username)
                .getSingleResult();
    }

    public Patient getByEmail(String email){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.email = :email", Patient.class)
                .setParameter("email",email)
                .getSingleResult();
    }

    public List<Doctor> getPatients(int doctorId){
        return entityManager.createQuery("SELECT p FROM Doctor pm inner join pm.patients p WHERE pm.id = : doctorId", Doctor.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    public List<Patient> getPatientsWithoutDoc(){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.doctorId = : NULL", Patient.class)
                .getResultList();

    }


}
