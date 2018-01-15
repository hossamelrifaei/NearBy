package data;

import base.Constants;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hossam on 1/15/18.
 */

public interface PlacesService {
    @GET("nearbysearch/json?radius=500&key=" + Constants.AMP_API_KEY)
    Single<PlacesResponse> getPlacesNearBy(@Query("location") String location);
}
