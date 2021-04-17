package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import org.restlet.resource.ServerResource;

public class ResourceUtils {

    public static void checkRole(ServerResource serverResource, String role)  throws AuthorizationException {
        if (!serverResource.isInRole(role)) {
            throw new AuthorizationException( "You're not authorized to do this");
        }
    }

}