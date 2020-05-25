package com.zeta.nova.constant;

/**
 * <p>Gender Enum holds are the supported Gender in the system</p>
 */
public enum Gender {
    FEMALE("female"), MALE("male");
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    /**
     * <p>Return the gender enum on the bases of given value.</p>
     *
     * @param value
     * @return gender
     */
    public static Gender getValue(String value) {
        for (Gender e : Gender.values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        return null;// not found
    }
}
