package com.zeta.nova.booking.process;

import com.zeta.nova.model.Cab;
import com.zeta.nova.model.CustomerRequest;
import com.zeta.nova.service.impl.CabRideRegService;
import com.zeta.nova.service.impl.CustomerRequestService;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * This class run the async thread to book the cab in same order in which ride offer request received.
 */
@Slf4j
public class Reserve implements Runnable {

    private final CabRideRegService cabService;

    private final CustomerRequestService crs;

    public Reserve(CabRideRegService cabService, CustomerRequestService crs) {
        this.cabService = cabService;
        this.crs = crs;
    }

    /**
     * <p>Run will book the cab in async mode in the same order in which request are ride offered.</p>
     */
    @Override
    public void run() {
        while (true) {
            Cab cab = null;
            try {
                cab = cabService.getCab();
                synchronized (cab) {
                    while (!cab.isReserved()) {
                        CustomerRequest req = crs.getCustomerRequestQueue();
                        //Allocate a seat to pax
                        if (!cab.blockSeat(req)) {
                            crs.request(req);
                        }
                    }
                    //If a seat is not allocated to pax,
                    // put the pax back in waiting queue.
                    if ((cab.isReserved())) {
                        CustomerRequest pax = cab.getExtraPax();
                        if (Objects.nonNull(pax)) {
                            crs.request(pax);
                        }
                    }
                    log.info("**************** BOOKED and Starting VROOOOOOOM ****************");
                }
            } catch (InterruptedException e) {
                log.error("Cab booking stuck with an error.", e);
            }
        }
    }
}
