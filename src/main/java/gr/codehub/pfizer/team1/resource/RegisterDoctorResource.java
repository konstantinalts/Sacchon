package gr.codehub.pfizer.team1.resource;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class RegisterDoctorResource extends ServerResource{

        @Post
        public DoctorRepresentation registerDoctor(DoctorRepresentation doctorRepresentation){
            return null;
        }
    }


