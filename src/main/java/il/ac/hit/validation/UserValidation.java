package il.ac.hit.validation;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * Defines a validation that can be applied to a user and combined with other
 * validations through the Combinator design pattern.
 */
@FunctionalInterface
public interface UserValidation extends Function<User, ValidationResult> {
    /**
     * Combines this validation with another validation using logical AND.
     *
     * @param other the second validation
     * @return a validation that succeeds only when both validations succeed
     */
    default UserValidation and(UserValidation other) {
        Objects.requireNonNull(other, "other validation cannot be null");

        // Stop after the first failure so its precise reason is preserved.
        return user -> {
            ValidationResult firstResult = apply(user);
            return firstResult.isValid() ? other.apply(user) : firstResult;
        };
    }

    /**
     * Combines this validation with another validation using logical OR.
     *
     * @param other the second validation
     * @return a validation that succeeds when at least one validation succeeds
     */
    default UserValidation or(UserValidation other) {
        Objects.requireNonNull(other, "other validation cannot be null");

        return user -> {
            ValidationResult firstResult = apply(user);
            if (firstResult.isValid()) {
                return firstResult;
            }

            // Both failure reasons are useful only when both checks fail.
            ValidationResult secondResult = other.apply(user);
            return secondResult.isValid()
                    ? secondResult
                    : invalidWithCombinedReasons(firstResult, secondResult);
        };
    }

    /**
     * Combines this validation with another validation using logical XOR.
     *
     * @param other the second validation
     * @return a validation that succeeds when exactly one validation succeeds
     */
    default UserValidation xor(UserValidation other) {
        Objects.requireNonNull(other, "other validation cannot be null");

        return user -> {
            ValidationResult firstResult = apply(user);
            ValidationResult secondResult = other.apply(user);
            if (firstResult.isValid() != secondResult.isValid()) {
                return new Valid();
            }

            // Equal truth values do not satisfy exclusive OR.
            return new Invalid("Exactly one validation must succeed");
        };
    }

    /**
     * Combines repeated validations using logical AND.
     *
     * @param validations the validations that must all succeed
     * @return a combined validation
     */
    static UserValidation all(UserValidation... validations) {
        requireValidations(validations);

        return user -> {
            for (UserValidation validation : validations) {
                ValidationResult result = validation.apply(user);
                if (!result.isValid()) {
                    return result;
                }
            }

            // The empty set also satisfies the universal all condition.
            return new Valid();
        };
    }

    /**
     * Combines repeated validations so that none of them may succeed.
     *
     * @param validations the validations that must all fail
     * @return a combined validation
     */
    static UserValidation none(UserValidation... validations) {
        requireValidations(validations);

        return user -> {
            for (UserValidation validation : validations) {
                if (validation.apply(user).isValid()) {
                    return new Invalid("At least one validation succeeded");
                }
            }

            // No supplied validation succeeded.
            return new Valid();
        };
    }

    /**
     * Checks whether the email address ends with "il".
     *
     * @return the email suffix validation
     */
    static UserValidation emailEndsWithIL() {
        return user -> user.getEmail().endsWith("il")
                ? new Valid()
                : new Invalid("Email must end with il");
    }

    /**
     * Checks whether the email address contains more than ten characters.
     *
     * @return the email length validation
     */
    static UserValidation emailLengthBiggerThan10() {
        return user -> user.getEmail().length() > 10
                ? new Valid()
                : new Invalid("Email length must be greater than 10");
    }

    /**
     * Checks whether the password contains more than eight characters.
     *
     * @return the password length validation
     */
    static UserValidation passwordLengthBiggerThan8() {
        return user -> user.getPassword().length() > 8
                ? new Valid()
                : new Invalid("Password length must be greater than 8");
    }

    /**
     * Checks whether every password character is a letter or a number.
     *
     * @return the password character validation
     */
    static UserValidation passwordIncludesLettersNumbersOnly() {
        return user -> user.getPassword().matches("[A-Za-z0-9]+")
                ? new Valid()
                : new Invalid("Password must include letters and numbers only");
    }

    /**
     * Checks whether the password includes a dollar sign.
     *
     * @return the dollar-sign validation
     */
    static UserValidation passwordIncludesDollarSign() {
        return user -> user.getPassword().contains("$")
                ? new Valid()
                : new Invalid("Password must include a dollar sign");
    }

    /**
     * Checks whether the password differs from the username.
     *
     * @return the username and password comparison validation
     */
    static UserValidation passwordIsDifferentFromUsername() {
        return user -> !user.getPassword().equals(user.getUsername())
                ? new Valid()
                : new Invalid("Password must be different from username");
    }

    /**
     * Checks whether the user's age is greater than 18.
     *
     * @return the age validation
     */
    static UserValidation ageBiggerThan18() {
        return user -> user.getAge() > 18
                ? new Valid()
                : new Invalid("Age must be greater than 18");
    }

    /**
     * Checks whether the username contains more than eight characters.
     *
     * @return the username length validation
     */
    static UserValidation usernameLengthBiggerThan8() {
        return user -> user.getUsername().length() > 8
                ? new Valid()
                : new Invalid("Username length must be greater than 8");
    }

    /**
     * Verifies the repeated-parameter array used by combination methods.
     *
     * @param validations the validations to verify
     */
    private static void requireValidations(UserValidation[] validations) {
        Objects.requireNonNull(validations, "validations cannot be null");
        Arrays.stream(validations)
                .forEach(validation -> Objects.requireNonNull(
                        validation,
                        "validation cannot be null"));
    }

    /**
     * Creates one invalid result from two validation failures.
     *
     * @param firstResult the first failure
     * @param secondResult the second failure
     * @return an invalid result containing both available reasons
     */
    private static ValidationResult invalidWithCombinedReasons(
            ValidationResult firstResult,
            ValidationResult secondResult) {
        String firstReason = firstResult.getReason().orElse("First validation failed");
        String secondReason = secondResult.getReason().orElse("Second validation failed");
        return new Invalid(firstReason + "; " + secondReason);
    }
}


