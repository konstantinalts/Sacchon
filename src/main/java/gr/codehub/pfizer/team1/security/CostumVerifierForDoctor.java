package gr.codehub.pfizer.team1.security;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;

import javax.persistence.EntityManager;

public class CostumVerifierForDoctor extends SecretVerifier {
    @Override
    public int verify(String username, char[] password) {

        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);

        Doctor doctor = doctorRepository.getByUsername(username);
            if (doctor == null)
                return SecretVerifier.RESULT_INVALID;
        String passwordInDb = doctor.getPassword();
        if (compare(passwordInDb.toCharArray(),password)){
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add(new Role(doctor.getRole()));
            return SecretVerifier.RESULT_VALID;
        }
        return SecretVerifier.RESULT_INVALID;
    }
}
