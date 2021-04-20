package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class DoctorRepository extends Repository<Doctor, Integer>{

    private EntityManager entityManager;
    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;

    }

    @Override
    public Class<Doctor> getEntityClass() { return Doctor.class; }

    @Override
    public String getClassName() { return Doctor.class.getName(); }

    public Doctor getByLastName(String lname){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.lname = :lname", Doctor.class)
                .setParameter("lname",lname)
                .getSingleResult();
    }

    public Doctor getByEmail(String email){
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.email = :email", Doctor.class)
                .setParameter("email",email)
                .getSingleResult();
    }

    public Doctor getByUsername(String username){
        try{
            return entityManager.createQuery("SELECT p FROM Doctor p WHERE p.username = :username", Doctor.class)
                .setParameter("username",username)
                .getSingleResult();
    } catch (Exception e){
            return null;
        }
    }


}
