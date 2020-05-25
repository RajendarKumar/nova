package com.zeta.nova.model;

import com.zeta.nova.constant.Gender;
import lombok.*;

/**
 * <p>This Customer object hold the properties customer.</p>
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
public class Customer {

    @NonNull
    private final String name;

    @NonNull
    private final Gender gender;


}
