package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import gr.codehub.pfizer.team1.representation.LoginDoctorRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class LoginDoctorResource extends ServerResource {

    private String username;

    @Override
    protected void doInit() {username = getAttribute(username);}

    @Post("json")
    public ApiResult<LoginDoctorRepresentation> login(LoginDoctorRepresentation loginDoctorRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.getByUsername(username);

        LoginDoctorRepresentation loginDoctorRepresentation1 = new LoginDoctorRepresentation(doctor);
        em.close();
        return new ApiResult<>(loginDoctorRepresentation1, 200, "succesfully login");

    }
}
