package zeenea.sdk.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Signifies that a public API (public class, method or field) is subject to incompatible changes, or even removal, in a future release.
 * <br>
 * An API bearing this annotation is exempt from any compatibility guarantees made by its containing library.
 * Note that the presence of this annotation implies nothing about the quality or performance of the API in question, only the fact that it is not "API-frozen."
 * <br>
 * It is generally safe for applications to depend on beta APIs, at the cost of some extra work during upgrades.
 */
@Retention(value = RetentionPolicy.SOURCE)
@Target(value = {ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, TYPE})
@Documented
public @interface Beta {
}
