package com.zeta.nova.model;

import com.zeta.nova.constant.Gender;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>This cab object hold the algorithm to board the customer and request.</p>
 *
 * @author Rajendar Kumar
 * @version 1.0
 * @since 2020-05-25
 */
@RequiredArgsConstructor
@Slf4j
public class Cab {

    @NonNull
    private final Car car;

    private final Set<CustomerRequest> malePax = new HashSet<CustomerRequest>();
    private final Set<CustomerRequest> femalePax = new HashSet<CustomerRequest>();
    private final AtomicBoolean isReserved = new AtomicBoolean(false);
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private CustomerRequest extraPax;

    /**
     * <p>Method handle the algorithm to accommodate pax as per below rules
     * <li>maximum 4 pax are allowed</li>
     * <li>All pax are female</li>
     * <li>or all pax are male</li>
     * <li>or two pax are female and two are male</li>
     * <p>
     *
     * @param req
     * @return @boolean
     */
    public synchronized boolean blockSeat(final CustomerRequest req) {
        log.info("blockSeat##################################################################################");

        boolean isJoined = false;
        if (isReserved()) {
            return false; //ride is already full, so no seat available for new pax
        }
        if (req.getCustomer().getGender() == Gender.MALE) {//case where all are male
            malePax.add(req);
        } else if (req.getCustomer().getGender() == Gender.FEMALE) {//case where all are female
            femalePax.add(req);
        }
        //CASE: Where 3 same gender pax have joined and next two request from from different gender.
        //ex MMM FF : so deboard a male and board a female, so that cab should not wait
        if (paxBoarded() == 5 && malePaxBoarded() == 3) {
            Iterator<CustomerRequest> iterator = malePax.iterator();
            CustomerRequest extraPax = iterator.hasNext() ? iterator.next() : null;
            setExtraPax(extraPax);
            malePax.remove(extraPax);
        } else if (paxBoarded() == 5 && femalePaxBoarded() == 3) {
            Iterator<CustomerRequest> iterator = femalePax.iterator();
            CustomerRequest extraPax = iterator.hasNext() ? iterator.next() : null;
            setExtraPax(extraPax);
            femalePax.remove(extraPax);
        }
        if (isRideFull()) {
            reserve();
        }
        return true;
    }

    /**
     * <p>Checks if all seats already occupied or not in cab.</p>
     *
     * @return @{@link Boolean}
     */
    private synchronized boolean isRideFull() {
        final int maleBoarded = malePax.size();
        final int femaleBoarded = femalePax.size();
        return (maleBoarded == 4) || (femaleBoarded == 4) || (maleBoarded == 2 && femaleBoarded == 2);
    }

    private synchronized void reserve() {
        isReserved.getAndSet(true);
    }

    public synchronized boolean isReserved() {
        return isReserved.get();
    }

    public int malePaxBoarded() {
        return malePax.size();
    }

    public int femalePaxBoarded() {
        return femalePax.size();
    }

    public int paxBoarded() {
        return femalePax.size() + malePax.size();
    }

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

    public Car getCar() {
        return this.car;
    }

    /**
     * <p>It will hold the pax in case of swap the pax to achieve fairness
     * and release cab without any extra wait time. If any pax combination available.</p>
     *
     * @return @{@link CustomerRequest}
     */
    public synchronized CustomerRequest getExtraPax() {
        CustomerRequest pax = this.extraPax;//new CustomerRequest(this.extraPax.getCustomer(), this.extraPax.getDateTime());
        this.setExtraPax(null);
        return pax;
    }

    /**
     * Used to remove the extra pax info in case of swap a pax.
     *
     * @param extraPax
     */
    private synchronized void setExtraPax(CustomerRequest extraPax) {
        this.extraPax = extraPax;
    }
}
