package di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import base.BaseActivity;
import base.BaseController;
import dagger.android.AndroidInjector;

/**
 * Created by hossam on 1/14/18.
 */

@ActivityScope
public class ScreenInjector {
    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String, AndroidInjector<Controller>> cache = new HashMap<>();

    @Inject
    ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors) {
        this.screenInjectors = screenInjectors;
    }

    static ScreenInjector get(Activity activity) {
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Controller must be hosted by BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }

    void inject(Controller controller) {
        if (!(controller instanceof BaseController)) {
            throw new IllegalArgumentException("Controller must extend BaseController");
        }

        String instanceId = controller.getInstanceId();
        if (cache.containsKey(instanceId)) {
            cache.get(instanceId).inject(controller);
            return;
        }

        AndroidInjector.Factory<Controller> injectorFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
        AndroidInjector<Controller> injector = injectorFactory.create(controller);
        cache.put(instanceId, injector);
        injector.inject(controller);
    }

    void clear(Controller controller) {
        cache.remove(controller.getInstanceId());
    }
}
