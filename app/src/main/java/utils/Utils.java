package utils;

import android.net.Uri;
import android.support.annotation.NonNull;

import model.Location;

/**
 * Created by hossam on 1/16/18.
 */

public class Utils {
    public static final String ROUNDED_TRANSFORMATION_KEY = "rounded";
    private static final String MAP_IMAGE_ZOOM = "15";
    private static final String MAP_IMAGE_SIZE = "200x200";

    @NonNull
    public static String formatLocationString(double latitude, double longitude) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(latitude);
        stringBuilder.append(",");
        stringBuilder.append(longitude);
        return stringBuilder.toString();
    }

    public static String formatLocationImageUrl(Location location) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("maps.google.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("staticmap")
                .appendQueryParameter("zoom", MAP_IMAGE_ZOOM)
                .appendQueryParameter("size", MAP_IMAGE_SIZE)
                .appendQueryParameter("center", formatLocationString(location.lat(), location.lng()));
        return builder.build().toString();
    }
}
