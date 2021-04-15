package gr.codehub.pfizer.team1.router;



import gr.codehub.pfizer.team1.resource.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {


    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router protectedResources() {

        Router router = new Router();

//        router.attach("/patient", PatientListResource.class);
//        router.attach("/patient{id}", PatientResource.class);
//
//        router.attach("/doctor", DoctorListResource.class);
//        router.attach("/doctor/{id}", DoctorResource.class);
//        router.attach("/doctor/delete/{id}", DoctorResource.class);
//
//        router.attach("/medical_data", MediDataRepoListResource.class);
//        router.attach("/medical_data/{id}", MediDataRepoResource.class);
//
//        router.attach("/medical_report", AdviceListResource.class);
//        router.attach("/medical_report/{id}", AdviceResource.class);

        return router;
    }

    public Router publicResources() {
        Router router = new Router();

        router.attach("/patient", PatientListResource.class);
        router.attach("/patient{id}", PatientResource.class);

        router.attach("/doctor", DoctorListResource.class);
        router.attach("/doctor/{id}", DoctorResource.class);
        router.attach("/doctor/delete/{id}", DoctorResource.class);

        router.attach("/medical_data", MediDataRepoListResource.class);
        router.attach("/medical_data/{id}", MediDataRepoResource.class);

        router.attach("/medical_report", AdviceListResource.class);
        router.attach("/medical_report/{id}", AdviceResource.class);

        //       router.attach("/home", HomeServerResource.class);
        return router;


    }
}