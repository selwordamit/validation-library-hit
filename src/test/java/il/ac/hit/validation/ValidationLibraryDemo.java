package il.ac.hit.validation;

import java.util.Comparator;

/**
 * Demonstrates and checks the public API without external test libraries.
 */
public final class ValidationLibraryDemo {
    /**
     * Prevents creation of demo-class instances.
     */
    private ValidationLibraryDemo() {
    }

    /**
     * Runs representative factory, template, and combinator operations.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        User basicUser = UserFactory.createUser(
                "basic", "administrator", "admin@example.co.il", "abc123$xyz", 34);
        User premiumUser = UserFactory.createUser(
                "premium", "premiumUser", "premium@example.co.il", "safe12345", 25);

        // The same shape appears in the grading document's sample program.
        UserValidation emailValidation = UserValidation
                .emailLengthBiggerThan10()
                .and(UserValidation.emailEndsWithIL());
        require(emailValidation.apply(basicUser).isValid(), "email validation failed");

        UserValidation completeValidation = UserValidation.all(
                UserValidation.emailEndsWithIL(),
                UserValidation.emailLengthBiggerThan10(),
                UserValidation.passwordLengthBiggerThan8(),
                UserValidation.passwordIncludesDollarSign(),
                UserValidation.passwordIsDifferentFromUsername(),
                UserValidation.ageBiggerThan18(),
                UserValidation.usernameLengthBiggerThan8());
        require(completeValidation.apply(basicUser).isValid(), "all validation failed");

        // Template Method behavior: callers choose the comparison operation.
        User[] users = {basicUser, premiumUser};
        UserUtils.sort(users, Comparator.comparingInt(User::getAge));
        require(users[0] == premiumUser, "age sorting failed");

        require(UserValidation.passwordIncludesLettersNumbersOnly()
                .apply(premiumUser).isValid(), "letters-and-numbers validation failed");
        require(UserValidation.none(UserValidation.passwordIncludesDollarSign())
                .apply(premiumUser).isValid(), "none validation failed");
        require(UserValidation.ageBiggerThan18()
                .xor(UserValidation.passwordIncludesDollarSign())
                .apply(premiumUser).isValid(), "xor validation failed");

        System.out.println("All validation library checks passed.");
    }

    /**
     * Stops the demo when an expected condition is false.
     *
     * @param condition the expected condition
     * @param message the failure message
     */
    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}


