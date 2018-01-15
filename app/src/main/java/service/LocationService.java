package service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import di.ActivityScope;
import places.OnLocationResult;


/**
 * Created by hossam on 1/15/18.
 */

@ActivityScope
public class LocationService {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;


    public LocationService(Context context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }


    @SuppressLint("MissingPermission")
    public void getLocation(OnLocationResult listener) {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                listener.onLocationSuccess(formatLocationString(task.getResult().getLatitude(), task.getResult().getLongitude()));
            } else {

            }

        });
    }

    @NonNull
    private String formatLocationString(double latitude, double longitude) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(latitude);
        stringBuilder.append(",");
        stringBuilder.append(longitude);
        return stringBuilder.toString();
    }
}
