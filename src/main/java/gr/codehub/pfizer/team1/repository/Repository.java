package gr.codehub.pfizer.team1.repository;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class Repository<T,K> {

    private final EntityManager entityManager;

    protected Repository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //Create , insert
    public T save(T t){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);

            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Class<T> getEntityClass();
    public abstract String getClassName();
    // Read select
    public T read(K id){
        return entityManager.find(getEntityClass(), id);

    }

    public List<T> findAll(){
        return entityManager.createQuery("from "+getClassName()).getResultList();
    }


    // Update
    public T update( T t)
    {

        return save(t);
    }


    // Delete
    public boolean delete(K id) {
        T t = read(id);
        if (t == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}