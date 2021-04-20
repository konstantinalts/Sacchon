package gr.codehub.pfizer.team1.resource;

import gr.codehub.pfizer.team1.exception.AuthorizationException;
import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.model.Doctor;
import gr.codehub.pfizer.team1.repository.DoctorRepository;
import gr.codehub.pfizer.team1.representation.DoctorRepresentation;
import gr.codehub.pfizer.team1.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class DoctorResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {id = Integer.parseInt(getAttribute("id")); }

    @Get("json")
    public ApiResult<DoctorRepresentation> getDoctor(){

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_OWNER);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);

        DoctorRepresentation doctorRepresentation = new DoctorRepresentation(doctor);
        em.close();
        return new ApiResult<>(doctorRepresentation,200,"ok");
    }

    @Post("json")
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
        doctor.setChiefDoctor(doctorRepresentation.getChiefDoctor());

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
