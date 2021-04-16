package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.representation.PatientRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class RegisterPatientResource extends ServerResource {
    @Post
    public PatientRepresentation registerPatient(PatientRepresentation patientRepresentation){
        return null;
    }
}
