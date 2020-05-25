package com.zeta.nova.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>This CustomerRequest object hold the properties CustomerRequest.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerRequest {

    @NonNull
    private final Customer customer;

    @NonNull
    private final LocalDateTime dateTime;

}