package gr.codehub.pfizer.team1.resource;


import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.repository.DataRepository;
import gr.codehub.pfizer.team1.representation.DataRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class MediDataRepoResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public DataRepresentation getMediDataRepo(){
        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        MediDataRepo mediDataRepo = dataRepository.read(id);

        DataRepresentation dataRepresentation = new DataRepresentation(mediDataRepo);
        em.close();
        return dataRepresentation;
    }

    @Put("json")
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

