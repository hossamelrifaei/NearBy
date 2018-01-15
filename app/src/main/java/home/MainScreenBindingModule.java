package home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import places.PlacesComponent;
import places.PlacesController;

/**
 * Created by hossam on 1/14/18.
 */

@Module(subcomponents = {PlacesComponent.class})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(PlacesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingInjector(PlacesComponent.Builder builder);
}
