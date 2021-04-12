package gr.codehub.pfizer.team1;

import gr.codehub.pfizer.team1.jpautil.JpaUtil;
import gr.codehub.pfizer.team1.router.CustomRouter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class MainApp extends Application {

    public static final Logger LOGGER = Engine.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {

        EntityManager em = JpaUtil.getEntityManager();

        em.close();

        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/v1", new MainApp());
        c.start();

        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/v1/product");
    }

    @Override
    public Restlet createInboundRoot() {
        CustomRouter customRouter = new CustomRouter(this);
        Router publicRouter = customRouter.publicResources();

        return publicRouter;
    }

    }
