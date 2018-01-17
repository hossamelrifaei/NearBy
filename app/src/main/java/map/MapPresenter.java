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

    @Override
    public void onCameraChanged(LatLng latLng, int zoom) {
        locationService.updateLocation(latLng);
        getAddress(latLng);
    }

    public void locationSelected() {
        screenNavigator.pop();
    }

    public void getAddress(LatLng location) {
        addressRequester.getAddress(Utils.formatLocationString(location.latitude, location.longitude))
                    .subscribe(viewModel.addressUpdated());

    }

}
