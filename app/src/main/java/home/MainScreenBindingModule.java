package home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import map.MapComponent;
import map.MapController;
import places.PlacesComponent;
import places.PlacesController;

/**
 * Created by hossam on 1/14/18.
 */

@Module(subcomponents = {PlacesComponent.class, MapComponent.class})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(PlacesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindPlacesInjector(PlacesComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(MapController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindMapInjector(MapComponent.Builder builder);
}
