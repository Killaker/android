package android.support.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
public @interface Read {
    RequiresPermission value();
}
