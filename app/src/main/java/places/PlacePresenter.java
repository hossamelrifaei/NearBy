package places;

import android.annotation.SuppressLint;
import android.support.annotation.RequiresPermission;

import javax.inject.Inject;

import data.PlacesRequester;
import di.ScreenScope;
import home.OnPermissionListener;
import service.LocationService;
import ui.ScreenNavigator;

/**
 * Created by hossam on 1/15/18.
 */
@ScreenScope
public class PlacePresenter implements OnLocationResult {

    private final PlacesViewModel viewModel;
    private final ScreenNavigator screenNavigator;
    private final PlacesRequester placesRequester;

    private final LocationService locationService;
    private OnPermissionListener permissionListener;

    @Inject
    PlacePresenter(PlacesViewModel viewModel, ScreenNavigator screenNavigator, PlacesRequester placesRequester, LocationService locationService) {
        this.viewModel = viewModel;
        this.screenNavigator = screenNavigator;
        this.placesRequester = placesRequester;
        this.locationService = locationService;

    }


    @SuppressLint("SupportAnnotationUsage")
    @RequiresPermission
    public void getCurrentLocation() {
        if (permissionListener.HasPermission()) {
            locationService.getLocation(this);
        } else {
            permissionListener.requestPermission();
        }

    }

    public void setPermissionListener(OnPermissionListener permissionListener) {
        this.permissionListener = permissionListener;

    }

    public void start() {
        getCurrentLocation();
    }

    @Override
    public void onLocationSuccess(String location) {
        placesRequester.getPlaces(location)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.placesUpdated(), viewModel.onError());
    }
}
