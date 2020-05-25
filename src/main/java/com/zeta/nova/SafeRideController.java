package com.zeta.nova;

import com.zeta.nova.model.Car;
import com.zeta.nova.model.Customer;
import com.zeta.nova.service.api.RideRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ride controller that is used to request and offer ride.
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@RestController
@RequestMapping("/nova")
@Slf4j
public class SafeRideController {

    @Autowired
    private RideRequest<Car> offerRideServiceImpl;

    @Autowired
    private RideRequest<Customer> customerRideRequest;

    @RequestMapping("/ride/hello")
    public String hello() {
        return "{hello}";
    }

    /**
     * <p>Endpoint to request a ride by customer.</>
     *
     * @param customer
     * @return {@link Boolean}
     */
    @RequestMapping(value = "/ride_request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> requestRide(@RequestBody final Customer customer) {
        log.info("Ride request received for customer.");
        return ResponseEntity.ok(customerRideRequest.submitRequest(customer));
    }

    /**
     * <p>Endpoint to offer a ride by car owner.</>
     *
     * @param car
     * @return {@link Boolean}
     */
    @RequestMapping(value = "/ride_offer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> offerRide(@RequestBody final Car car) {
        log.info("Ride offer request received for the car.");
        return ResponseEntity.ok(offerRideServiceImpl.submitRequest(car));
    }
}
