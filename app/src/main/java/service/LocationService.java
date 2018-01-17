package service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.RequiresPermission;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import di.ActivityScope;
import io.reactivex.Single;


/**
 * Created by hossam on 1/15/18.
 */

@ActivityScope
public class LocationService {

    private LatLng cashedLocation = null;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    public LocationService(Context context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public void updateLocation(LatLng latLng) {
        cashedLocation = latLng;
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public Single<LatLng> getLocation() {
        return Single.create(singleSubscriber -> {
            if (cashedLocation != null) {
                singleSubscriber.onSuccess(cashedLocation);
            } else {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        updateLocation(new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude()));
                        singleSubscriber.onSuccess(cashedLocation);
                    }

                });
            }
        });
    }
}
