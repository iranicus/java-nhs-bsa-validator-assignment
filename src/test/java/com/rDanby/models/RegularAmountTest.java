package com.rDanby.models;

import com.rDanby.domain.models.RegularAmount;
import com.rDanby.domain.models.enums.Frequency;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Richard James Danby
 * @version 1.0, 07/03/2020
 */
class RegularAmountTest {
    private static Validator validator;
    private RegularAmount classUnderTest;

    @BeforeAll
    static void setupValidation() {
        // hibernate validator implementation of ValidatorFactory
        validator = Validation
                .byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
    }

    @Test
    void anyFrequency_nonNumericOrBlankAmount_noValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(Frequency.WEEK, "words").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(0, validations.size());
    }

    @Test
    void anyAmount_nullFrequency_noValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(null, "35.00").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(0, validations.size());
    }

    @Test
    void weekFrequency_anyAmount_noValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(Frequency.WEEK, "35.00").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(0, validations.size());
    }

    @Test
    void monthFrequency_anyAmount_noValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(Frequency.MONTH, "35.00").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(0, validations.size());
    }

    @Test
    void twoWeekFourWeekQuarterOrYearFrequency_twoFourThirteenFiftyTwoWeeks_amountDivides_noValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(Frequency.TWO_WEEK, "20").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(0, validations.size());
    }

    @Test
    void twoWeekFourWeekQuarterOrYearFrequency_twoFourThirteenFiftyTwoWeeks_amountNotDivides_ValidationError() {
        // Arrange
        classUnderTest = new RegularAmount.RegularAmountBuilder(Frequency.YEAR, "12.5").build();

        // Act
        Set<ConstraintViolation<RegularAmount>> validations = validator.validate(classUnderTest);

        // Assert
        assertEquals(1, validations.size());
    }
}
