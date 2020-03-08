package com.rDanby.domain.validators;

import com.rDanby.domain.models.RegularAmount;
import com.rDanby.domain.models.enums.Frequency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Custom class level constraint to inspect the amount depending
 * on the selected frequency, after a calculation results in a whole number
 */
public class RegularAmountValidator implements ConstraintValidator<RegularAmountConstraint, RegularAmount> {
    public void initialize(RegularAmountConstraint constraintAnnotation) {
    }

    public boolean isValid(RegularAmount ra, ConstraintValidatorContext constraintValidatorContext) {
        String questionableAmt = ra.getAmount();
        Pattern checkAlphaNumeric = Pattern.compile("[a-zA-Z]");
        boolean autoAcceptAmts = (
                questionableAmt == null ||
                        ra.getFrequency() == null ||
                        questionableAmt.length() == 0 ||
                        checkAlphaNumeric.matcher(questionableAmt).find()
        );
        if(autoAcceptAmts) {
            return true;
        }

        if (ra.getFrequency().equals(Frequency.TWO_WEEK)) {
            double amt = Double.parseDouble(questionableAmt);
            return ((amt / 2) % 2 == 0);
        }
        if (ra.getFrequency().equals(Frequency.FOUR_WEEK)) {
            double amt = Double.parseDouble(questionableAmt);
            return ((amt / 4) % 2 == 0);
        }
        if (ra.getFrequency().equals(Frequency.QUARTER)) {
            double amt = Double.parseDouble(questionableAmt);
            return ((amt / 13) % 2 == 0);
        }
        if (ra.getFrequency().equals(Frequency.YEAR)) {
            double amt = Double.parseDouble(questionableAmt);
            return ((amt / 52) % 2 == 0);
        }
        // Unknown cases not covered in acceptance criteria
        return true;
    }
}
