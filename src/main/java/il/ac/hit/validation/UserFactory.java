package il.ac.hit.validation;

import java.util.Locale;
import java.util.Objects;

/**
 * Creates the requested concrete user type through a single factory method.
 */
public final class UserFactory {
    /**
     * Prevents creation of utility-class instances.
     */
    private UserFactory() {
    }

    /**
     * Creates a user according to the supplied membership type.
     *
     * @param type basic, premium, or platinum
     * @param username the user's sign-in name
     * @param email the user's email address
     * @param password the user's password
     * @param age the user's age in years
     * @return a concrete user of the requested type
     * @throws IllegalArgumentException when the type is unsupported
     */
    public static User createUser(
            String type,
            String username,
            String email,
            String password,
            int age) {
        Objects.requireNonNull(type, "type cannot be null");

        // The factory centralizes the selection of the concrete user class.
        return switch (type.toLowerCase(Locale.ROOT)) {
            case "basic" -> new BasicUser(username, email, password, age);
            case "premium" -> new PremiumUser(username, email, password, age);
            case "platinum" -> new PlatinumUser(username, email, password, age);
            default -> throw new IllegalArgumentException("Unsupported user type: " + type);
        };
    }
}


