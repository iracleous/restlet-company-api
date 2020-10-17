package gr.codehub.restapi.router;

import gr.codehub.restapi.resource.impl.CustomerListResourceImpl;
import gr.codehub.restapi.resource.impl.CustomerResourceImpl;
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
        router.attach("/customer/", CustomerListResourceImpl.class);


        return router;
    }



}
