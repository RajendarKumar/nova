package com.zeta.nova;

import com.zeta.nova.constant.Gender;
import com.zeta.nova.model.Car;
import com.zeta.nova.model.Customer;
import com.zeta.nova.service.api.RideRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SafeRideControllerTest {

    @InjectMocks
    private SafeRideController rideController;

    @Mock
    private RideRequest<Car> offerRideServiceImpl;

    @Mock
    private RideRequest<Customer> customerRideRequest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRequestRideTrue() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(offerRideServiceImpl.submitRequest(Mockito.any(Car.class))).thenReturn(Boolean.TRUE);

        Car car = new Car("KA01CA1234");

        ResponseEntity<Boolean> booleanResponseEntity = rideController.offerRide(car);
        Assertions.assertTrue(booleanResponseEntity.getBody());
        Assertions.assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void testRequestRideFalse() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(offerRideServiceImpl.submitRequest(Mockito.any(Car.class))).thenReturn(Boolean.FALSE);

        Car car = new Car("KA01CA1234");

        ResponseEntity<Boolean> booleanResponseEntity = rideController.offerRide(car);
        Assertions.assertFalse(booleanResponseEntity.getBody());
        Assertions.assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void offerRideTrue() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(customerRideRequest.submitRequest(Mockito.any(Customer.class))).thenReturn(Boolean.TRUE);

        Customer customer = new Customer("Rajendar", Gender.MALE);

        ResponseEntity<Boolean> booleanResponseEntity = rideController.requestRide(customer);
        Assertions.assertTrue(booleanResponseEntity.getBody());
        Assertions.assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void offerRideFalse() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(customerRideRequest.submitRequest(Mockito.any(Customer.class))).thenReturn(Boolean.FALSE);

        Customer customer = new Customer("Rajendar", Gender.MALE);

        ResponseEntity<Boolean> booleanResponseEntity = rideController.requestRide(customer);
        Assertions.assertFalse(booleanResponseEntity.getBody());
        Assertions.assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }
}