package com.zeta.nova.service.impl;


import com.zeta.nova.model.Customer;
import com.zeta.nova.service.api.RegService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>This CustomerRegService class implements interface @{@link RegService}.
 * This class holds the Customer information.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class CustomerRegService implements RegService<Customer> {
    private final Set<Customer> customers = Collections.synchronizedSet(new HashSet<Customer>());

    /**
     * <p>Register the customer with system and hold the values</>
     *
     * @param customer
     * @return
     */
    @Override
    public boolean register(final Customer customer) {
        return customers.add(customer);
    }

    /**
     * <p>Deregister the customer with system and hold the values</>
     *
     * @param customer
     * @return
     */
    @Override
    public boolean deregister(final Customer customer) {
        return customers.remove(customer);
    }

    /**
     * <p>Method to test object already register with system.</p>
     *
     * @param customer
     * @return {@link Boolean}
     */
    @Override
    public boolean isRegistered(final Customer customer) {
        return customers.contains(customer);
    }
}
