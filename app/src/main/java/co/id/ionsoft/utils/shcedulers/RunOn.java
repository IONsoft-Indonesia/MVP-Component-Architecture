package co.id.ionsoft.utils.shcedulers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedulerType value() default SchedulerType.IO;
}
