package at.spengergasse.springtest.domain.persistence;

import at.spengergasse.springtest.domain.Email;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class EmailValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidEmail() {
        Email validEmail = new Email("ABC22349@spengergasse.com");

        // Validate the Email object
        Set<ConstraintViolation<Email>> violations = validator.validate(validEmail);

        // Assert that there are no validation violations
        assertTrue(violations.isEmpty(), "Expected no validation errors for a valid email");
    }

    @Test
    public void testInvalidEmail() {
        // Test with an invalid email
        Email invalidEmail = new Email("invalid-email");

        // Validate the Email object
        Set<ConstraintViolation<Email>> violations = validator.validate(invalidEmail);

        // Assert that there are validation violations
        assertFalse(violations.isEmpty(), "Expected validation errors for an invalid email");

        //print out the violation messages
        for (ConstraintViolation<Email> violation : violations) {
            System.out.println(violation.getMessage());
        }

        // specific error message if needed
        assertEquals("Invalid email", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmptyEmail() {
        // Test with an empty email
        Email emptyEmail = new Email("");

        // Validate the Email object
        Set<ConstraintViolation<Email>> violations = validator.validate(emptyEmail);

        // Assert that there are validation violations
        assertFalse(violations.isEmpty(), "Expected validation errors for an empty email");

        // specific error message if needed
        assertEquals("Invalid email", violations.iterator().next().getMessage());
    }
}
