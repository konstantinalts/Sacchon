package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.repository.AdviceRepository;
import gr.codehub.pfizer.team1.repository.DataRepository;
import gr.codehub.pfizer.team1.representation.AdviceRepresentation;
import gr.codehub.pfizer.team1.representation.DataRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class AdviceListResource extends ServerResource {

    @Get("json")
    public List<AdviceRepresentation> getDoctorAdvice(){
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        List<DoctorAdvice> doctorAdvices = adviceRepository.findAll();
        em.close();

        List<AdviceRepresentation> adviceRepresentationList = new ArrayList<>();
        for (DoctorAdvice a : doctorAdvices)
            adviceRepresentationList.add(new AdviceRepresentation(a));

        return adviceRepresentationList;
    }

    @Post("json")
    public AdviceRepresentation add(AdviceRepresentation adviceRepresentation){

        if (adviceRepresentation == null) return null;
        if (adviceRepresentation.getDate() == null) return null;
        if (adviceRepresentation.getMedication() == null) return null;
        // Ta int, float den mporoun na dilwthoun me ==
        if (adviceRepresentation.getDosage() == Integer.parseInt(null)) return null;
        if (adviceRepresentation.getDescription() == null) return null;


        DoctorAdvice doctorAdvice = adviceRepresentation.createDoctorAdvice();
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        adviceRepository.save(doctorAdvice);
        AdviceRepresentation a = new AdviceRepresentation(doctorAdvice);
        return a;

    }
}
