package gr.codehub.restapi.resource.util;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ResourceUtils {

    /**
     * Indicates if the authenticated client user associated to the current
     * request is in the given role name.
     *
     * @param serverResource    *   The current server resource.
     * @param roles     *            The roles to check.
     * @throws ResourceException
     *             In case the current authenticated user has not sufficient
     *             permission.
     */
    public static void checkRole(ServerResource serverResource, List<String> roles)
            throws ResourceException {
        AtomicBoolean hasAuthentication = new AtomicBoolean(false);
        roles.forEach(role -> {if (serverResource.isInRole(role)) {
            hasAuthentication.set(true);}});

        if (!hasAuthentication.get()) {
            throw new ResourceException(
                    Status.CLIENT_ERROR_FORBIDDEN.getCode(),
                    "You're not authorized to send this call.");
        }
    }

}
