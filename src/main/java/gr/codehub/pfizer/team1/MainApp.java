package gr.codehub.pfizer.team1;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;

import javax.persistence.EntityManager;

public class MainApp extends Application {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        em.close();

    }
}
