package base;

import android.app.Application;

import javax.inject.Inject;

import di.ActivityInjector;

/**
 * Created by hossam on 1/14/18.
 */

public class NearByApplication extends Application {

    @Inject
    ActivityInjector activityInjector;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
