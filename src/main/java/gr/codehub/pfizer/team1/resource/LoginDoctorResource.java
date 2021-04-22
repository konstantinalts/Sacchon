package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import gr.codehub.pfizer.team1.representation.LoginDoctorRepresentation;
import org.restlet.resource.Post;

import javax.persistence.EntityManager;

public class LoginDoctorResource {

    @Post("json")
    public ApiResult<LoginDoctorRepresentation> login(LoginDoctorRepresentation loginDoctorRepresentation) {

        if (loginDoctorRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the customer");
            return new ApiResult<>(null, 400, "No email added to create the costumer");
        if (loginDoctorRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username added to create the costumer");
        if (loginDoctorRepresentation.getPassword() == null)
            return new ApiResult<>(null, 400, "No password added to create the costumer");

        Doctor doctor = loginDoctorRepresentation.create();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        return new ApiResult<>(new DoctorRepresentation(doctor), 200,
                "The customer was succesfully created");
    }
}
