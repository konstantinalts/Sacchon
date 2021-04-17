package gr.codehub.pfizer.team1.representation;

import gr.codehub.pfizer.team1.model.User;

import java.util.Date;

public class UserRepresantation {

    private int id;
    private String fname;
    private String lname;
    private Date birthdate;
    private String address;
    private String telephone;
    private String email;
    private String username;
    private String password;
    private String role;


    public UserRepresantation(User user) {

        if (user != null) {
            id = user.getId();
            fname = user.getFname();
            lname = user.getLname();
            birthdate = user.getBirthdate();
            address = user.getAddress();
            telephone = user.getTelephone();
            email = user.getEmail();
            username = user.getUsername();
            password = user.getPassword();
            role = user.getRole();
        }
    }
}

