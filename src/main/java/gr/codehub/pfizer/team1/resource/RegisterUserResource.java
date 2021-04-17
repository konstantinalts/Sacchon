package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.User;
import gr.codehub.pfizer.team1.repository.UserRepository;
import gr.codehub.pfizer.team1.representation.UserRepresantation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RegisterUserResource extends ServerResource {

    @Get("json")
    public ApiResult<List<UserRepresantation>> getUsers(){

        try{
            ResourceUtils.checkRole(this, Shield.ROLE_OWNER);
        } catch (AuthorizationException e){
            return new ApiResult<>(null,500,e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();
        UserRepository userRepository = new UserRepository(em);

        List<User> users = userRepository.findAll();
        em.close();

        List<UserRepresantation> userRepresantationList =
                users.stream()
                .map(UserRepresantation::new)
                .collect(toList());
        return new ApiResult<>(userRepresantationList, 200, "all went good");
    }
}
