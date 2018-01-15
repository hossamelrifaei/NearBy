package base;

import javax.inject.Singleton;

import dagger.Component;
import data.PlacesServiceModule;
import network.ServiceModule;

/**
 * Created by hossam on 1/14/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ActivityBindingModule.class, ServiceModule.class, PlacesServiceModule.class})
public interface ApplicationComponent {

    void inject(NearByApplication myApplication);
}
