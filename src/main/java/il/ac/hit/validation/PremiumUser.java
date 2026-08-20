package il.ac.hit.validation;

/**
 * Represents a user with the premium membership type.
 */
public class PremiumUser extends User {
    /**
     * Creates a premium user.
     *
     * @param username the user's sign-in name
     * @param email the user's email address
     * @param password the user's password
     * @param age the user's age in years
     */
    public PremiumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}


