package com.zeta.nova.service.impl;

import com.zeta.nova.model.Customer;
import com.zeta.nova.model.CustomerRequest;
import com.zeta.nova.service.api.RegService;
import com.zeta.nova.service.api.RideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>This BookRideServiceImpl interface implements @{@link RideRequest}.
 * This class validate the @customer's existence in system and submit the ride request.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class BookRideServiceImpl implements RideRequest<Customer> {

    @Autowired
    private RegService<Customer> customerRegService;

    @Autowired
    private CustomerRequestService customerRequestService;

    /**
     * <p>Valid the customer's existence in system and submit the ride request</>
     *
     * @param customer
     * @return
     */
    @Override
    public boolean submitRequest(final Customer customer) {
        if (customerRegService.isRegistered(customer)) {
            return customerRequestService.request(new CustomerRequest(customer, LocalDateTime.now()));
        }
        return false;
    }
}
