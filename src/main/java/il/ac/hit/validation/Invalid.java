package il.ac.hit.validation;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a failed validation result and its reason.
 */
public final class Invalid implements ValidationResult {
    /** The explanation for the validation failure. */
    private final String reason;

    /**
     * Creates a failed validation result.
     *
     * @param reason the explanation for the failure
     */
    public Invalid(String reason) {
        this.reason = Objects.requireNonNull(reason, "reason cannot be null");
    }

    /**
     * Reports that this result is invalid.
     *
     * @return always false
     */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
     * Returns the failure reason.
     *
     * @return an optional containing the failure reason
     */
    @Override
    public Optional<String> getReason() {
        return Optional.of(reason);
    }
}


