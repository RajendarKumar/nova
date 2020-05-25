package com.zeta.nova.service.impl;

import com.zeta.nova.model.Car;
import com.zeta.nova.service.api.RegService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>This CarRegService interface implements @{@link RegService}.
 * This class holds the car information.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Service
public class CarRegService implements RegService<Car> {

    private final Set<Car> cars = Collections.synchronizedSet(new HashSet<Car>());

    /**
     * <p>Register the car with system and hold the values</>
     *
     * @param car
     * @return
     */
    @Override
    public boolean register(final Car car) {
        return cars.add(car);
    }

    /**
     * <p>Deregister the car with system and hold the values</>
     *
     * @param car
     * @return
     */
    @Override
    public boolean deregister(final Car car) {
        return cars.remove(car);
    }

    /**
     * <p>Method to test object already register with system.</p>
     *
     * @param car
     * @return {@link Boolean}
     */
    @Override
    public boolean isRegistered(final Car car) {
        return cars.contains(car);
    }
}
