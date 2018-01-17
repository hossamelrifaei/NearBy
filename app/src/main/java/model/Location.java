package model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by hossam on 1/14/18.
 */

@AutoValue
public abstract class Location {

    public static JsonAdapter<Location> jsonAdapter(Moshi moshi) {
        return new AutoValue_Location.MoshiJsonAdapter(moshi);
    }

    public abstract double lat();

    public abstract double lng();

}
