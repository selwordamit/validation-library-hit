package il.ac.hit.validation;

/**
 * Represents a user with the basic membership type.
 */
public class BasicUser extends User {
    /**
     * Creates a basic user.
     *
     * @param username the user's sign-in name
     * @param email the user's email address
     * @param password the user's password
     * @param age the user's age in years
     */
    public BasicUser(String username, String email, String password, int age) {
        super(username, email, password, age);
    }
}


