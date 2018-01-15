package home;

import com.bluelinelabs.conductor.Controller;
import com.nearby.R;

import base.BaseActivity;
import places.PlacesController;

/**
 * Created by hossam on 1/14/18.
 */

public class MainActivity extends BaseActivity {
    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initializeScreen() {
        return new PlacesController();
    }


}
