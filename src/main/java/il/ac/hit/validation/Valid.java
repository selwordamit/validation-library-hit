package il.ac.hit.validation;

import java.util.Optional;

/**
 * Represents a successful validation result.
 */
public final class Valid implements ValidationResult {
    /**
     * Creates a successful validation result.
     */
    public Valid() {
    }

    /**
     * Reports that this result is valid.
     *
     * @return always true
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * Returns no failure reason because this result is valid.
     *
     * @return an empty optional
     */
    @Override
    public Optional<String> getReason() {
        return Optional.empty();
    }
}


