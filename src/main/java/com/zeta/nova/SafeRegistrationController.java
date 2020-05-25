package com.zeta.nova;

import com.zeta.nova.model.Car;
import com.zeta.nova.model.Customer;
import com.zeta.nova.service.api.RegService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The registration controller that is used to register customer and car.
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */
@RestController
@RequestMapping("/nova")
@Slf4j
public class SafeRegistrationController {

    @Autowired
    private RegService<Car> carRegService;

    @Autowired
    private RegService<Customer> customerRegService;

    @RequestMapping("/reg/hello")
    public String hello() {
        return "{hello}";
    }

    /**
     * <p>Endpoint to register a car</>
     *
     * @param car
     * @return {@link Boolean}
     */
    @RequestMapping(value = "/register_car", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerCar(@RequestBody final Car car) {
        log.info("Registration request received for car.");
        return ResponseEntity.ok(carRegService.register(car));
    }

    /**
     * <p>Endpoint to register a customer</>
     *
     * @param customer {@link Customer}
     * @return {@link Boolean}
     */
    @RequestMapping(value = "/register_customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerCustomer(@RequestBody final Customer customer) {
        log.info("Registration request received for customer.");
        return ResponseEntity.ok(customerRegService.register(customer));
    }
}
