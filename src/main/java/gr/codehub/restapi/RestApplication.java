package gr.codehub.restapi;

import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.router.CustomRouter;
import gr.codehub.restapi.security.CustomRole;
import gr.codehub.restapi.security.Shield;
import gr.codehub.restapi.security.cors.CustomCorsFilter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class RestApplication extends Application {

    public static final Logger LOGGER = Engine.getLogger(RestApplication.class);

    public static void main(String[] args) throws Exception {
        LOGGER.info("CRM application starting...");
        EntityManager em = JpaUtil.getEntityManager();
        em.close();

        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/app", new RestApplication());
        c.start();

        LOGGER.config("Sample Web API started");
        LOGGER.fine("URL: http://localhost:9000/app/customer/1");
    }

    public RestApplication() {

        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, CustomRole.ROLE_ADMIN.getRoleName()));
        getRoles().add(new Role(this, CustomRole.ROLE_OWNER.getRoleName()));
        getRoles().add(new Role(this, CustomRole.ROLE_USER.getRoleName()));

    }

    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);
        CustomCorsFilter corsFilter = new CustomCorsFilter(this);

        ChallengeAuthenticator apiGuard = shield.createApiGuard();
        Router publicRouter = customRouter.publicResources();
        Router apiRouter = customRouter.createApiRouter();

        publicRouter.attachDefault(apiGuard);
        apiGuard.setNext(apiRouter);

        return corsFilter.createCorsFilter(publicRouter);
    }
}
