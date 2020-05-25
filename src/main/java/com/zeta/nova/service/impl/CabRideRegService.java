package com.zeta.nova.service.impl;

import com.zeta.nova.model.Cab;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p>This CabRideRegService holds the offered rides in the queue.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class CabRideRegService {

    private static final int DEFAULT_CAPACITY = 1000;
    private final BlockingQueue<Cab> cabQueue = new PriorityBlockingQueue<Cab>(DEFAULT_CAPACITY,
            (c1, c2) -> c1.getLocalDateTime().compareTo(c2.getLocalDateTime()));

    /**
     * Save the offered ride details
     *
     * @param cab
     * @return
     */
    public boolean registerRide(final Cab cab) {

        return cabQueue.add(cab);
    }

    /**
     * Return the Cab that is offering a ride if available else waits for cab.
     *
     * @return cab
     * @throws InterruptedException
     */
    public Cab getCab() throws InterruptedException {
        return cabQueue.take();
    }
}
