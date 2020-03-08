package com.rDanby.domain.models;

import com.rDanby.domain.models.enums.Frequency;
import com.rDanby.domain.validators.RegularAmountConstraint;

/**
 * Pre-defined by assessment brief but altered to use the
 * Builder design pattern with class level custom constraint
 *
 * @author  richard.j.danby
 * @version 1.0, 07/03/2020
 */
@RegularAmountConstraint
public final class RegularAmount {
    private final Frequency frequency;

    private final String amount;

    private RegularAmount(RegularAmountBuilder builder) {
        this.frequency = builder.frequency;
        this.amount = builder.amount;
    }

    public static class RegularAmountBuilder {
        private final Frequency frequency;
        private final String amount;

        public RegularAmountBuilder(Frequency frequency, String amount) {
            this.frequency = frequency;
            this.amount = amount;
        }

        public RegularAmount build() {
            return new RegularAmount(this);
        }
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RegularAmount {" +
                "\n\tfrequency = " + frequency +
                ",\n\tamount = '" + amount + '\'' +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegularAmount that = (RegularAmount) o;
        return (getFrequency() == that.getFrequency() && getAmount().equals(that.getAmount()));
    }

    @Override
    public int hashCode() {
        return (getFrequency().hashCode() + getAmount().hashCode() * 7);
    }
}
