package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.repository.AdviceRepository;
import gr.codehub.pfizer.team1.representation.AdviceRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;


public class AdviceResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public ApiResult<AdviceRepresentation> getDoctorAdvice(){

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e){
            return new ApiResult<>(null, 500,e.getMessage());
        }
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        DoctorAdvice doctorAdvice  = adviceRepository.read(id);

        AdviceRepresentation adviceRepresentation = new AdviceRepresentation(doctorAdvice);
        em.close();
        return new ApiResult<>(adviceRepresentation,200,"ok");
    }

    @Post("json")
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
