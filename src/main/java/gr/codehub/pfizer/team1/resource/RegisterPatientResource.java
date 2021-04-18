package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.repository.PatientRepository;
import gr.codehub.pfizer.team1.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class RegisterPatientResource extends ServerResource {

    @Post("json")
    public ApiResult<PatientRepresentation> registerPatient(PatientRepresentation patientRepresentation) {

        if (patientRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the user");
        if (patientRepresentation.getFname() == null)
            return new ApiResult<>(null, 400, "No first name added to create the user");
        if (patientRepresentation.getLname() == null)
            return new ApiResult<>(null, 400, "No last name added to create the user");
        if (patientRepresentation.getBirthdate() == null)
            return new ApiResult<>(null, 400, "No birthdate added to create the user");
        if (patientRepresentation.getTelephone() == null)
            return new ApiResult<>(null, 400, "No telephone added to create the user");
        if (patientRepresentation.getEmail() == null)
            return new ApiResult<>(null, 400, "No email added to create the user");
        if (patientRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username added to create the user");
        if (patientRepresentation.getPassword() == null)
            return new ApiResult<>(null, 400, "No password added to create the user");
        if (usernameExists(patientRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");
        if (emailExists(patientRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate email");

        Patient patient = patientRepresentation.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        return new ApiResult<>(new PatientRepresentation(patient), 200,
                "The user was succesfully created");
    }

    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Patient p = null;
        try {
            p = em.createQuery("SELECT p FROM Patient p WHERE p.username= :candidate", Patient.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return p != null;
    }

    public boolean emailExists(String candidateEmail) {
        EntityManager em = JpaUtil.getEntityManager();
        Patient p = null;
        try {
            p = em.createQuery("SELECT p FROM Patient p WHERE p.email= :candidate", Patient.class)
                    .setParameter("candidate", candidateEmail)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return p != null;
    }


    @Get
    public boolean usernameExists() {
        String candidateUsername = "";
        String candidateEmail= "";
        try {
            candidateUsername = getQueryValue("username");
            candidateEmail = getQueryValue("email");
        } catch (Exception e) {
            return false;
        }
        return usernameExists(candidateUsername) && emailExists(candidateEmail);
    }
}
