package gr.codehub.pfizer.team1.resource;


import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.repository.Repository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DoctorListResource extends ServerResource {



    @Get("json")
    public List<DoctorRepresentation> getDoctor(){
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        List<Doctor> doctors = doctorRepository.findAll();
        em.close();

        List<DoctorRepresentation> doctorRepresentationList = new ArrayList<>();
        for (Doctor d : doctors)
            doctorRepresentationList.add(new DoctorRepresentation(d));

        return doctorRepresentationList;
    }

    @Post("json")
    public DoctorRepresentation add(DoctorRepresentation doctorRepresentation){

        if (doctorRepresentation == null) return null;
        if (doctorRepresentation.getFname() == null) return null;
        if (doctorRepresentation.getLname() == null) return null;
        if (doctorRepresentation.getBirthdate() == null) return null;
        if (doctorRepresentation.getAddress() == null) return null;
//        if (doctorRepresentation.getTelephone() == null) return null;
        if (doctorRepresentation.getEmail() == null) return null;
        if (doctorRepresentation.getUsername() == null) return null;
        if (doctorRepresentation.getPassword() == null) return null;

        Doctor doctor = doctorRepresentation.createDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        DoctorRepresentation d = new DoctorRepresentation(doctor);
        return d;

    }

}
