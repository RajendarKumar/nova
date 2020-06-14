package com.zeta.nova;

import com.zeta.nova.model.Car;
import com.zeta.nova.model.Customer;
import com.zeta.nova.service.api.RegService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SafeRegistrationControllerTest {

    @InjectMocks
    SafeRegistrationController registrationController;

    @Mock
    private RegService<Car> carRegService;

    @Mock
    private RegService<Customer> customerRegService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRegisterCarTrue(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(carRegService.register(any(Car.class))).thenReturn(true);

        Car car = new Car("KA01CA1234");

        ResponseEntity<Boolean> booleanResponseEntity = registrationController.registerCar(car);
        assertTrue(booleanResponseEntity.getBody());
        assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testRegisterCarFalse(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(carRegService.register(any(Car.class))).thenReturn(false);

        Car car = new Car("KA01CA1234");

        ResponseEntity<Boolean> booleanResponseEntity = registrationController.registerCar(car);
        assertFalse(booleanResponseEntity.getBody());
        assertTrue(booleanResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @AfterEach
    void tearDown() {
    }
}