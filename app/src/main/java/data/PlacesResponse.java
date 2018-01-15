package data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import model.Place;

/**
 * Created by hossam on 1/15/18.
 */

@AutoValue
public abstract class PlacesResponse {

    public static JsonAdapter<PlacesResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_PlacesResponse.MoshiJsonAdapter(moshi);
    }

    public abstract List<Place> results();
}
