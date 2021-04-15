package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.repository.AdviceRepository;
import gr.codehub.pfizer.team1.representation.AdviceRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;


public class AdviceResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public AdviceRepresentation getDoctorAdvice(){
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        DoctorAdvice doctorAdvice  = adviceRepository.read(id);

        AdviceRepresentation adviceRepresentation = new AdviceRepresentation(doctorAdvice);
        em.close();
        return adviceRepresentation;
    }

    @Put("json")
    public AdviceRepresentation addDoctorAdvice(AdviceRepresentation adviceRepresentation){
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        DoctorAdvice doctorAdvice  = adviceRepository.read(id);


        doctorAdvice.setDate(adviceRepresentation.getDate());
//        doctorAdvice.setMedication(adviceRepresentation.getMedication());
        doctorAdvice.setDosage(adviceRepresentation.getDosage());
        doctorAdvice.setDescription(adviceRepresentation.getDescription());


        AdviceRepresentation adviceRepresentation1 = new AdviceRepresentation(doctorAdvice);
        em.close();
        return adviceRepresentation1;
    }

    @Delete("txt")
    public boolean deleteDoctorAdvice(){
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        return adviceRepository.delete(id);
    }
}
