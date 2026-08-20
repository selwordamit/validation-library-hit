package il.ac.hit.validation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Supplies reusable operations for collections of users.
 */
public final class UserUtils {
    /**
     * Prevents creation of utility-class instances.
     */
    private UserUtils() {
    }

    /**
     * Sorts an array in place according to a caller-supplied comparison step.
     * The sorting algorithm supplies the template and the comparator supplies
     * the operation that may vary.
     *
     * @param users the array to sort
     * @param comparator the functionality used to compare two users
     * @param <T> a concrete user type
     */
    public static <T extends User> void sort(
            T[] users,
            Comparator<? super T> comparator) {
        Objects.requireNonNull(users, "users cannot be null");
        Objects.requireNonNull(comparator, "comparator cannot be null");

        // Arrays.sort executes the fixed sorting template.
        Arrays.sort(users, comparator);
    }
}


