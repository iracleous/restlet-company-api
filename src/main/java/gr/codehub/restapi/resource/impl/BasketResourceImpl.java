package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Basket;
import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.BasketRepository;
import gr.codehub.restapi.repository.CustomerRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.BasketRepresentation;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.representation.ProductRepresentation;
import gr.codehub.restapi.resource.BasketResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BasketResourceImpl extends ServerResource implements BasketResource {

    private BasketRepository basketRepository;
    private CustomerRepository customerRepository;
    private EntityManager em;

    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            basketRepository = new BasketRepository(em);

        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() {
        em.close();
    }

    @Override
    public BasketRepresentation getBasket() throws NotFoundException {
        return null;
    }

    @Override
    public void removeBasket() throws NotFoundException {

    }

    @Override
    public BasketRepresentation updateBasket(ProductRepresentation product) throws NotFoundException, BadEntityException {
        return null;
    }

    @Override
    public BasketRepresentation createBasket() throws NotFoundException, BadEntityException {

        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());
        ResourceUtils.checkRole(this, roles);


        Basket basket = new Basket();
        basket.setCreationDate(new Date());
        basketRepository.save(basket);
        return BasketRepresentation.getBasketRepresentation(basket);


    }

    @Override
    public BasketRepresentation assignCustomer(BasketRepresentation basketRep) throws NotFoundException, BadEntityException {

         if (basketRep == null) throw new NotFoundException("NotFoundException");

        Optional<Customer> customerFromDb = customerRepository.findById(basketRep.getCustomerId());
        Optional<Basket> basketFromDb = basketRepository.findById(basketRep.getBasketId()) ;

        if (!customerFromDb.isPresent()) throw new NotFoundException("Not such customer");
        if (!basketFromDb.isPresent()) throw new NotFoundException("Not such basket");

        Basket basket = basketFromDb.get();
        Customer customer = customerFromDb.get();
        basket.setCustomer(customer);
        basketRepository.save(basket);

        return BasketRepresentation.getBasketRepresentation(basket);
    }
}
