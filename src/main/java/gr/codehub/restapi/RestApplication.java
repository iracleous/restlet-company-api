package gr.codehub.restapi;

import gr.codehub.restapi.repository.util.JpaUtil;
import org.restlet.Application;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class RestApplication extends Application {


    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);


    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        em.close();
    }


}
