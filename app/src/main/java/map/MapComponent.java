package map;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import di.ScreenScope;

/**
 * Created by hossam on 1/17/18.
 */

@ScreenScope
@Subcomponent
public interface MapComponent extends AndroidInjector<MapController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MapController> {

        @Override
        public void seedInstance(MapController instance) {

        }
    }
}
