package com.zeta.nova.service.api;

/**
 * <p>This RideRequest interface implemented by all ride services.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */
public interface RideRequest<T> {
    /**
     * <p>Api to submit a request to the system.</p>
     *
     * @param t
     * @return {@link Boolean}
     */
    boolean submitRequest(T t);
}
