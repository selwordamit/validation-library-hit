package il.ac.hit.validation;

import java.util.Optional;

/**
 * Describes the outcome of applying a validation to a user.
 */
public interface ValidationResult {
    /**
     * Reports whether the validation succeeded.
     *
     * @return true when the user is valid; otherwise false
     */
    boolean isValid();

    /**
     * Returns the failure reason when the validation did not succeed.
     *
     * @return an empty optional for a valid result, or a reason for failure
     */
    Optional<String> getReason();
}


