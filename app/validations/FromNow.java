package validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by haroldo on 31/07/14.
 */
@Target(value={ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=FromNowValidator.class)
@play.data.Form.Display(name="validations.FromNow")
public @interface FromNow {

    String message() default "error.frowNow";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}