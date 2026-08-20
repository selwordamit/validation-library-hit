package il.ac.hit.validation;

/**
 * Represents a user with the platinum membership type.
 */
public class PlatinumUser extends User {
    /**
     * Creates a platinum user.
     *
     * @param username the user's sign-in name
     * @param email the user's email address
     * @param password the user's password
     * @param age the user's age in years
     */
    public PlatinumUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}


