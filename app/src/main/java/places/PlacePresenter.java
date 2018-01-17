package places;

import android.Manifest;
import android.annotation.SuppressLint;

import javax.inject.Inject;

import data.PlacesRequester;
import di.ScreenScope;
import io.reactivex.android.schedulers.AndroidSchedulers;
import service.LocationService;
import service.PermissionService;
import ui.ScreenNavigator;
import utils.Utils;

/**
 * Created by hossam on 1/15/18.
 */
@ScreenScope
public class PlacePresenter {

    private final PlacesViewModel viewModel;
    private final ScreenNavigator screenNavigator;
    private final PlacesRequester placesRequester;
    private final LocationService locationService;
    private final PermissionService permissionService;

    @Inject
    PlacePresenter(PlacesViewModel viewModel, ScreenNavigator screenNavigator, PlacesRequester placesRequester, LocationService locationService, PermissionService permissionService) {
        this.viewModel = viewModel;
        this.screenNavigator = screenNavigator;
        this.placesRequester = placesRequester;
        this.locationService = locationService;
        this.permissionService = permissionService;
    }


    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        if (permissionService.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            locationService.getLocation()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(location -> {
                        placesRequester.getPlaces(Utils.formatLocationString(location.latitude, location.longitude))
                                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                                .subscribe(viewModel.placesUpdated(), viewModel.onError());
                    });
        }

    }

    public void openMap() {
        screenNavigator.goToMap();
    }
}
