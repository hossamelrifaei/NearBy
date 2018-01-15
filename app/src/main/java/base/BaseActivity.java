package base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.nearby.R;

import java.util.UUID;

import javax.inject.Inject;

import di.Injector;
import di.ScreenInjector;
import ui.ScreenNavigator;

/**
 * Created by hossam on 1/14/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static String INSTANCE_ID_KEY = "instance_id";
    @Inject
    ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;
    private String instanceId;
    private Router router;

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initializeScreen();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }

        Injector.inject(this);
        setContentView(layoutRes());

        ViewGroup container = findViewById(R.id.screen_container);
        if (container == null) {
            throw new NullPointerException("Activity must have view with id: screen_container");
        }
        router = Conductor.attachRouter(this, container, savedInstanceState);
        screenNavigator.initWithRouter(router, initializeScreen());
        monitorBackStackForRouter();

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void monitorBackStackForRouter() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to,
                                        @Nullable Controller from,
                                        boolean isPush,
                                        @NonNull ViewGroup container,
                                        @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to,
                                          @Nullable Controller from,
                                          boolean isPush,
                                          @NonNull ViewGroup container,
                                          @NonNull ControllerChangeHandler handler) {
                if (!isPush && from != null) {
                    Injector.clearComponent(from);
                }

            }
        });
    }
}
