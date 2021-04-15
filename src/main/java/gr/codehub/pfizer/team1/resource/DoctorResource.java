package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class DoctorResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public DoctorRepresentation getDoctor(){
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);

        DoctorRepresentation doctorRepresentation = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation;
    }

    @Put("json")
    public DoctorRepresentation addDoctor(DoctorRepresentation doctorRepresentation){
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);


        doctor.setFname(doctorRepresentation.getFname());
        doctor.setLname(doctorRepresentation.getLname());
        doctor.setBirthdate(doctorRepresentation.getBirthdate());
        doctor.setAddress(doctorRepresentation.getAddress());
        doctor.setTelephone(doctorRepresentation.getTelephone());
        doctor.setEmail(doctorRepresentation.getEmail());
        doctor.setUsername(doctorRepresentation.getUsername());
        doctor.setPassword(doctorRepresentation.getPassword());
//        doctor.setChiefDoctor(doctorRepresentation.getChiefDoctor());

        DoctorRepresentation doctorRepresentation1 = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation1;
    }

    @Delete("txt")
    public boolean deleteDoctor(){
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        return doctorRepository.delete(id);
    }
}
