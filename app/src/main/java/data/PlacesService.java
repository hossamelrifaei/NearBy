package data;

import base.Constants;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hossam on 1/15/18.
 */

public interface PlacesService {
    @GET("place/nearbysearch/json?radius=200&key=" + Constants.MAP_API_KEY)
    Single<PlacesResponse> getPlacesNearBy(@Query("location") String location);

    @GET("geocode/json?" + Constants.MAP_API_KEY)
    Single<AddressResponse> getLocationAddress(@Query("latlng") String location);
}
