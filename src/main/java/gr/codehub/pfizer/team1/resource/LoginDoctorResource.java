package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class LoginDoctorResource extends ServerResource {

    @Post("json")
    public ApiResult<DoctorRepresentation> loginDoctor(DoctorRepresentation doctorRepresentation){
        if (doctorRepresentation == null)
            return new ApiResult<>(null,400,"No input data to check for that user");
        if (doctorRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400,"Username must not be empty");
        if (doctorRepresentation.getPassword() == null)
            return new ApiResult<>(null, 400,"Password must not be empty");
        if (usernameMatch(doctorRepresentation.getUsername()) && passwordMatch(doctorRepresentation.getPassword()))
            return new ApiResult<>(null,400,"Wrong username or password");


        return new ApiResult<>(doctorRepresentation,200,"Login successfully");
    }

    public boolean usernameMatch(String candidateUsername){
        EntityManager em = JpaUtil.getEntityManager();
        Doctor d = null;
        try{
            d = em.createQuery("SELECT d FROM Doctor d WHERE d.username= :candidate",Doctor.class)
                    .setParameter("candidate",candidateUsername)
                    .getSingleResult();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return d != null;
    }

    public boolean passwordMatch(String candidatePassword){
        EntityManager em = JpaUtil.getEntityManager();
        Doctor d = null;
        try{
            d = em.createQuery("SELECT d FROM Doctor d WHERE d.password= :candidate", Doctor.class)
                    .setParameter("candidate",candidatePassword)
                    .getSingleResult();

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return d != null;
    }
}
