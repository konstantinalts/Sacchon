package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.DoctorAdvice;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.repository.AdviceRepository;
import gr.codehub.pfizer.team1.repository.DataRepository;
import gr.codehub.pfizer.team1.representation.AdviceRepresentation;
import gr.codehub.pfizer.team1.representation.DataRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Resource;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class AdviceListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<AdviceRepresentation>> getDoctorAdvice(){

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }



        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);

        List<DoctorAdvice> doctorAdvices = adviceRepository.findAll();
        em.close();

        List<AdviceRepresentation> adviceRepresentationList =
                doctorAdvices.stream()
                        .map( p-> new AdviceRepresentation(p))
                        .collect(toList());

                //new ArrayList<>();
        //for (DoctorAdvice a : doctorAdvices)
       //     adviceRepresentationList.add(new AdviceRepresentation(a));

        return new ApiResult<>(adviceRepresentationList, 100, "okay") ;
    }

    @Post("json")
    public AdviceRepresentation add(AdviceRepresentation adviceRepresentation){

        if (adviceRepresentation == null) return null;
        if (adviceRepresentation.getDate() == null) return null;
        if (adviceRepresentation.getMedication() == null) return null;


        DoctorAdvice doctorAdvice = adviceRepresentation.createDoctorAdvice();
        EntityManager em = JpaUtil.getEntityManager();
        AdviceRepository adviceRepository = new AdviceRepository(em);
        adviceRepository.save(doctorAdvice);
        AdviceRepresentation a = new AdviceRepresentation(doctorAdvice);
        return a;

    }
}
