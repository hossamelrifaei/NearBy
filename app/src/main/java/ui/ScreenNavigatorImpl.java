package ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import di.ActivityScope;
import map.MapController;

/**
 * Created by hossam on 1/14/18.
 */

@ActivityScope
public class ScreenNavigatorImpl implements ScreenNavigator {
    private Router router;

    @Inject
    ScreenNavigatorImpl() {
    }

    @Override
    public void initWithRouter(Router router, Controller controller) {
        this.router = router;
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(controller));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }


    @Override
    public void goToMap() {
        if (router != null) {
            router.pushController(RouterTransaction.with(MapController.newInstance())
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }

    }
}
