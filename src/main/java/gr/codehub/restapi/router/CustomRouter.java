package gr.codehub.restapi.router;

import gr.codehub.restapi.resource.PingServerResource;
import gr.codehub.restapi.resource.impl.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router createApiRouter() {

        Router router = new Router(application.getContext());

        router.attach("/customer/{id}", CustomerResourceImpl.class);
        router.attach("/customer", CustomerListResourceImpl.class);

        router.attach("/product/{id}", ProductResourceImpl.class);
        router.attach("/product",  ProductListResourceImpl.class);


        router.attach("/basket",  BasketResourceImpl.class);
        router.attach("/basket/{id}",  BasketResourceImpl.class);
        return router;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        return router;
    }
}
