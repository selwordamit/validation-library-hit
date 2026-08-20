package il.ac.hit.validation;

import java.util.Objects;

/**
 * Represents a user whose details can be checked by the validation library.
 */
public class User {
    /** The name used by the user to sign in. */
    private final String username;

    /** The user's email address. */
    private final String email;

    /** The user's password. */
    private final String password;

    /** The user's age in years. */
    private final int age;

    /**
     * Creates a user.
     *
     * @param username the user's sign-in name
     * @param email the user's email address
     * @param password the user's password
     * @param age the user's age in years
     */
    public User(String username, String email, String password, int age) {
        this.username = Objects.requireNonNull(username, "username cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        this.password = Objects.requireNonNull(password, "password cannot be null");
        this.age = age;
    }

    /**
     * Returns the user's sign-in name.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's age.
     *
     * @return the age in years
     */
    public int getAge() {
        return age;
    }
}


