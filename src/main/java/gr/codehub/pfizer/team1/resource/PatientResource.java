package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Patient;
import gr.codehub.pfizer.team1.repository.PatientRepository;
import gr.codehub.pfizer.team1.representation.PatientRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class PatientResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public PatientRepresentation getPatient(){
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);

        PatientRepresentation patientRepresentation = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation;
    }

    @Put("json")
    public PatientRepresentation addPatient(PatientRepresentation patientRepresentation){
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);


        patient.setFname(patientRepresentation.getFname());
        patient.setLname(patientRepresentation.getLname());
        patient.setBirthdate(patientRepresentation.getBirthdate());
        patient.setAddress(patientRepresentation.getAddress());
        patient.setTelephone(patientRepresentation.getTelephone());
        patient.setEmail(patientRepresentation.getEmail());
        patient.setUsername(patientRepresentation.getUsername());
        patient.setPassword(patientRepresentation.getPassword());

        PatientRepresentation patientRepresentation1 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation1;
    }

    @Delete("txt")
    public boolean deletePatient(){
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        return patientRepository.delete(id);
    }
}
