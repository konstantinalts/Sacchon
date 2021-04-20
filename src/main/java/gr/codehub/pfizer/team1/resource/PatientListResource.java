package gr.codehub.pfizer.team1.resource;
import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.repository.PatientRepository;
import gr.codehub.pfizer.team1.representation.PatientRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class PatientListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient(){

        String role = "";

        try{
            ResourceUtils.checkRole(this, Shield.ROLE_OWNER); role="owner";
        }catch (AuthorizationException e){
        }
        try{
            ResourceUtils.checkRole(this, Shield.ROLE_USER); role="user";
        }catch (AuthorizationException e){
        }

        if (!role.equals("owner") && !role.equals("user"))
            return new ApiResult<>(null,500,"Not Authorized");


        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        List<Patient> patients = patientRepository.findAll();
        em.close();

        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
        for (Patient p : patients)
            patientRepresentationList.add(new PatientRepresentation(p));

        return new ApiResult<>(patientRepresentationList,200,"ok");
    }

    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientRepresentation){

        if (patientRepresentation == null) return null;
        if (patientRepresentation.getFname() == null) return null;
        if (patientRepresentation.getLname() == null) return null;
        if (patientRepresentation.getBirthdate() == null) return null;
        if (patientRepresentation.getAddress() == null) return null;
//        if (patientRepresentation.getTelephone() == null) return null;
        if (patientRepresentation.getEmail() == null) return null;
        if (patientRepresentation.getUsername() == null) return null;
        if (patientRepresentation.getPassword() == null) return null;

        Patient patient = patientRepresentation.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return p;

    }

}


