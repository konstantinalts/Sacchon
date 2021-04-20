package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.MediDataRepo;
import gr.codehub.pfizer.team1.repository.DataRepository;
import gr.codehub.pfizer.team1.representation.DataRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MediDataRepoListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<DataRepresentation>> getMediDataRepo(){

        String role = "";

        try{
            ResourceUtils.checkRole(this, Shield.ROLE_OWNER); role="owner";
        }catch (AuthorizationException e){
        }
        try{
            ResourceUtils.checkRole(this, Shield.ROLE_USER); role="user";
        }catch (AuthorizationException e){
        }

        if (!role.equals("owner") && !role.equals("user"))
            return new ApiResult<>(null,500,"Not Authorized");


        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        List<MediDataRepo> mediDataRepos = dataRepository.findAll();

        List<DataRepresentation> mediDataRepoRepresentationList = new ArrayList<>();
        for (MediDataRepo d : mediDataRepos)
            mediDataRepoRepresentationList.add(new DataRepresentation(d));

        return new ApiResult<>(mediDataRepoRepresentationList,200,"ok");
    }

    @Post("json")
    public DataRepresentation add(DataRepresentation dataRepresentation){

        if (dataRepresentation == null) return null;
        if (dataRepresentation.getDate() == null) return null;
        if (dataRepresentation.getTime() == null) return null;


        MediDataRepo mediDataRepo = dataRepresentation.createData();
        EntityManager em = JpaUtil.getEntityManager();
        DataRepository dataRepository = new DataRepository(em);
        dataRepository.save(mediDataRepo);
        DataRepresentation d = new DataRepresentation(mediDataRepo);
        return d;

    }
}
