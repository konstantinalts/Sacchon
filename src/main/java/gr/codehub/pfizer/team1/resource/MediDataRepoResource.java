package gr.codehub.pfizer.team1.resource;


import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.repository.DataRepository;
import gr.codehub.pfizer.team1.representation.DataRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class MediDataRepoResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public ApiResult<DataRepresentation> getMediDataRepo(){

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        MediDataRepo mediDataRepo = dataRepository.read(id);

        DataRepresentation dataRepresentation = new DataRepresentation(mediDataRepo);
        em.close();
        return new ApiResult<>(dataRepresentation,200,"ok");
    }

    @Post("json")
    public DataRepresentation addMediDataRepo(DataRepresentation dataRepresentation){
        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        MediDataRepo mediDataRepo = dataRepository.read(id);


        mediDataRepo.setDate(dataRepresentation.getDate());
        mediDataRepo.setTime(dataRepresentation.getTime());
        mediDataRepo.setGlucoseLevel(dataRepresentation.getGlucoseLevel());
        mediDataRepo.setCarbIntake(dataRepresentation.getCarbIntake());

        DataRepresentation dataRepresentation1 = new DataRepresentation(mediDataRepo);
        em.close();
        return dataRepresentation1;
    }

    @Delete("txt")
    public boolean deleteMediDataRepo(){
        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        return dataRepository.delete(id);
    }
}

