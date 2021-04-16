package gr.codehub.pfizer.team1.security;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.repository.PatientRepository;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;

import javax.persistence.EntityManager;

public class CustomVerifierForPatient extends SecretVerifier{
    @Override
    public int verify(String username, char[] password) {

        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        Patient patient = patientRepository.getByUsername(username);
            if (patient == null)
                return SecretVerifier.RESULT_INVALID;
        String passwordInDb = patient.getPassword();
        if (compare(passwordInDb.toCharArray(), password)){
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role( patient.getRole() ));
            return SecretVerifier.RESULT_VALID;
        }
        return SecretVerifier.RESULT_INVALID;
    }
}
