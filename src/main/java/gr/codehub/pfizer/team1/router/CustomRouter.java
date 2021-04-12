package gr.codehub.pfizer.team1.router;


//import gr.codehub.pfizer.hibernate.resource.*;
//import org.restlet.Application;
//import org.restlet.routing.Router;

public class CustomRouter {


    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/home", HomeServerResource.class);
        router.attach("/patient", PatienttListResource.class);
        router.attach("/patient{id}", PatientResource.class);

        router.attach("/doctor", DoctorListResource.class);
        router.attach("/doctor/{id}", DoctorResource.class);

        router.attach("/medical_data", MedicalDataListResource.class);
        router.attach("/medical_data/{id}", MedicalDataResource.class);

        router.attach("/medical_report", MedicalReportListResource.class);
        router.attach("/medical_report/{id}", MedicalReportResource.class);

        return router;
    }


}