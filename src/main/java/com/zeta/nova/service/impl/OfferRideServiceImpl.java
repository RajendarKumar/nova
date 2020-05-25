package com.zeta.nova.service.impl;

import com.zeta.nova.model.Cab;
import com.zeta.nova.model.Car;
import com.zeta.nova.service.api.RegService;
import com.zeta.nova.service.api.RideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>This OfferRideServiceImpl interface implements @{@link RideRequest}.
 * This class validate the @{@link Car}'s existence in system and submit the ride request.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class OfferRideServiceImpl implements RideRequest<Car> {

    @Autowired
    private RegService<Car> carRegService;
    @Autowired
    private CabRideRegService rideRegService;

    @Override
    public boolean submitRequest(Car car) {
        if (carRegService.isRegistered(car)) {
            rideRegService.registerRide(new Cab(car));
            return true;
        }
        return false;
    }
}
