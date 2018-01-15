package ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by hossam on 1/14/18.
 */

public interface ScreenNavigator {

    void initWithRouter(Router router, Controller controller);

    boolean pop();

    void clear();

    void goToMap();
}
