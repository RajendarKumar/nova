package com.zeta.nova.model;


import lombok.*;

/**
 * <p>This car object hold the car properties.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */

@Builder
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class Car {
    @NonNull
    private String regPlate;
}
