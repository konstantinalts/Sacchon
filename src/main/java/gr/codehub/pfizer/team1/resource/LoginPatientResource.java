package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.repository.PatientRepository;
import gr.codehub.pfizer.team1.representation.LoginDoctorRepresentation;
import gr.codehub.pfizer.team1.representation.LoginPatientRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class LoginPatientResource extends ServerResource {

    private String username;

    @Override
    protected void doInit() {username = getAttribute(username);}

    @Post("json")
    public ApiResult<LoginPatientRepresentation> login(LoginPatientRepresentation loginPatientRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.getByUsername(username);

        LoginPatientRepresentation loginPatientRepresentation1 = new LoginPatientRepresentation(patient);
        em.close();
        return new ApiResult<>(loginPatientRepresentation1, 200, "succesfully login");

    }

}
