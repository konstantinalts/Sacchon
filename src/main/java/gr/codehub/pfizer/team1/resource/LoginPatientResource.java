package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.representation.PatientRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class LoginPatientResource extends ServerResource {

    @Post("json")
    public ApiResult<PatientRepresentation> loginPatient(PatientRepresentation patientRepresentation){
        if (patientRepresentation == null)
            return new ApiResult<>(null,400,"No input data to check for that user");
        if (patientRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400,"Username must not be empty");
        if (patientRepresentation.getPassword() == null)
            return new ApiResult<>(null, 400,"Password must not be empty");
        if (usernameMatch(patientRepresentation.getUsername()) && passwordMatch(patientRepresentation.getPassword()))
            return new ApiResult<>(null,400,"Wrong username or password");


        return new ApiResult<>(patientRepresentation,200,"Login successfully");
    }

    public boolean usernameMatch(String candidateUsername){
        EntityManager em = JpaUtil.getEntityManager();
        Patient p = null;
        try{
            p = em.createQuery("SELECT p FROM Patient p WHERE p.username= :candidate",Patient.class)
                    .setParameter("candidate",candidateUsername)
                    .getSingleResult();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return p != null;
    }

    public boolean passwordMatch(String candidatePassword){
        EntityManager em = JpaUtil.getEntityManager();
        Patient p = null;
        try{
            p = em.createQuery("SELECT p FROM Patient p WHERE p.password= :candidate",Patient.class)
                    .setParameter("candidate",candidatePassword)
                    .getSingleResult();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return p != null;
    }
}
