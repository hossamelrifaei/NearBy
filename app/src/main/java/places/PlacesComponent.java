package places;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import di.ScreenScope;

/**
 * Created by hossam on 1/14/18.
 */

@ScreenScope
@Subcomponent
public interface PlacesComponent extends AndroidInjector<PlacesController> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlacesController> {

    }
}
