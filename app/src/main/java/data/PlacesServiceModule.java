package data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hossam on 1/15/18.
 */

@Module
public class PlacesServiceModule {
    @Provides
    @Singleton
    static PlacesService PlacesServiceModule(Retrofit retrofit) {
        return retrofit.create(PlacesService.class);
    }
}
