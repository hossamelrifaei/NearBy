package model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by hossam on 1/14/18.
 */

@AutoValue
public abstract class Place {

    public static JsonAdapter<Place> jsonAdapter(Moshi moshi) {
        return new AutoValue_Place.MoshiJsonAdapter(moshi);
    }

    public abstract Geometry geometry();

    public abstract String name();

    @Json(name = "vicinity")
    public abstract String address();

}
