package com.zeta.nova.service.api;

/**
 * <p>This RegService interface implemented by all register services.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */
public interface RegService<T> {

    /**
     * <p>Api to register an object with system.</p>
     *
     * @param t
     * @return {@link Boolean}
     */
    boolean register(T t);

    /**
     * <p>Api to deregister an object with system.</p>
     *
     * @param t
     * @return {@link Boolean}
     */
    boolean deregister(T t);

    /**
     * <p>Api to test object already register with system.</p>
     *
     * @param t
     * @return {@link Boolean}
     */
    boolean isRegistered(T t);
}
