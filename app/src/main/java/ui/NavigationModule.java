package ui;

import dagger.Binds;
import dagger.Module;

/**
 * Created by hossam on 1/14/18.
 */

@Module
public abstract class NavigationModule {
    @Binds
    abstract ScreenNavigator providesScreenNavigator(ScreenNavigatorImpl screenNavigator);
}
