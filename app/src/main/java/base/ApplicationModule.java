package base;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import service.LocationService;

/**
 * Created by hossam on 1/14/18.
 */

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    static LocationService provideLocationService(Context context) {
        return new LocationService(context);
    }

    @Provides
    Context providesApplicationContext() {
        return application;
    }
}
