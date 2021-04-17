package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;

public class RegisterDoctorResource extends ServerResource {

    @Post("json")
    public ApiResult<DoctorRepresentation> registerPatient(DoctorRepresentation doctorRepresentation) {

        if (doctorRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the customer");
        if (doctorRepresentation.getFname() == null)
            return new ApiResult<>(null, 400, "No first name added to create the costumer");
        if (doctorRepresentation.getLname() == null)
            return new ApiResult<>(null, 400, "No last name added to create the costumer");
        if (doctorRepresentation.getBirthdate() == null)
            return new ApiResult<>(null, 400, "No birthdate added to create the costumer");
        if (doctorRepresentation.getTelephone() == null)
            return new ApiResult<>(null, 400, "No telephone added to create the costumer");
        if (doctorRepresentation.getEmail() == null)
            return new ApiResult<>(null, 400, "No email added to create the costumer");
        if (doctorRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username added to create the costumer");
        if (doctorRepresentation.getPassword() == null)
            return new ApiResult<>(null, 400, "No password added to create the costumer");
        if (usernameExists(doctorRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");
        if (emailExists(doctorRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate email");

        Doctor doctor = doctorRepresentation.createDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        return new ApiResult<>(new DoctorRepresentation(doctor), 200,
                "The customer was succesfully created");
    }

    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Doctor d = null;
        try {
            d = em.createQuery("SELECT p FROM Doctor p WHERE p.username= :candidate", Doctor.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return d != null;
    }

    public boolean emailExists(String candidateEmail) {
        EntityManager em = JpaUtil.getEntityManager();
        Doctor d = null;
        try {
            d = em.createQuery("SELECT p FROM Doctor p WHERE p.email= :candidate", Doctor.class)
                    .setParameter("candidate", candidateEmail)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return d != null;
    }


    @Get
    public boolean usernameOrEmailExists() {
        String candidateUsername = "";
        String candidateEmail = "";
        try {
            candidateUsername = getQueryValue("username");
            candidateEmail = getQueryValue("email");
        } catch (Exception e) {
            return false;
        }
        return usernameExists(candidateUsername) && emailExists(candidateEmail);
    }
}


