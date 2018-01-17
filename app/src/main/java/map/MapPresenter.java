package map;

import android.annotation.SuppressLint;

import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import data.AddressRequester;
import io.reactivex.android.schedulers.AndroidSchedulers;
import service.LocationService;
import ui.ScreenNavigator;
import utils.Utils;

/**
 * Created by hossam on 1/17/18.
 */

public class MapPresenter implements OnCameraChangeListener {

    private final LocationService locationService;
    private final ScreenNavigator screenNavigator;
    private final AddressRequester addressRequester;
    private final MapViewModel viewModel;

    @Inject
    MapPresenter(LocationService locationService, ScreenNavigator screenNavigator, AddressRequester addressRequester, MapViewModel viewModel) {
        this.locationService = locationService;
        this.screenNavigator = screenNavigator;
        this.addressRequester = addressRequester;
        this.viewModel = viewModel;
    }

    @SuppressLint("MissingPermission")
    public void initMap() {
        locationService.getLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewModel.locationUpdated());
    }

    public LatLng getLocation() {
        return locationService.getCashedLocation();
    }

    @Override
    public void onCameraChanged(LatLng latLng, int zoom) {
        locationService.updateLocation(latLng);
        getAddress();
    }

    public void locationSelected() {
        screenNavigator.pop();
    }

    public void getAddress() {
        if (locationService.getCashedLocation() != null) {
            addressRequester.getAddress(Utils.formatLocationString(locationService.getCashedLocation().latitude, locationService.getCashedLocation().longitude))
                    .subscribe(viewModel.addressUpdated());
        } else {
            initMap();
        }
    }

}
