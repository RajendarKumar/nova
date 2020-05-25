package com.zeta.nova.service.impl;

import com.zeta.nova.model.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p>This CustomerRequestService class holds the ride request from customer.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class CustomerRequestService {
    private static final int DEFAULT_CAPACITY = 1000;

    private final BlockingQueue<CustomerRequest> customerRequestQueue =
            new PriorityBlockingQueue<CustomerRequest>(DEFAULT_CAPACITY, (c1, c2) -> c1.getDateTime().compareTo(c2.getDateTime()));

    /**
     * <p>save the customer ride request.</p>
     *
     * @param req
     * @return
     */
    public boolean request(final CustomerRequest req) {
        return customerRequestQueue.add(req);
    }

    /**
     * <p>return the customer ride request info if available else waits for request.</p>
     *
     * @param
     * @return customerRequest
     */
    public CustomerRequest getCustomerRequestQueue() throws InterruptedException {
        return customerRequestQueue.take();
    }
}