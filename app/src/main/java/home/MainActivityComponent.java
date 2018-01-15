package home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import di.ActivityScope;
import ui.NavigationModule;

/**
 * Created by hossam on 1/14/18.
 */
@ActivityScope
@Subcomponent(modules = {MainScreenBindingModule.class, NavigationModule.class,})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
