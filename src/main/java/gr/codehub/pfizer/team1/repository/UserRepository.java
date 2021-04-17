package gr.codehub.pfizer.team1.repository;

import gr.codehub.pfizer.team1.model.User;

import javax.persistence.EntityManager;

public class UserRepository extends Repository<User, Integer> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) { super(entityManager); }

    @Override
    public Class<User> getEntityClass() {return null;}

    @Override
    public String getClassName() {return null;}
}
