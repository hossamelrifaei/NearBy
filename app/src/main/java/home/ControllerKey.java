package home;

import com.bluelinelabs.conductor.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by hossam on 1/14/18.
 */

@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {
    Class<? extends Controller> value();
}
