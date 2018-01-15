package model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by hossam on 1/16/18.
 */

@AutoValue
public abstract class Geometry {

    public static JsonAdapter<Geometry> jsonAdapter(Moshi moshi) {
        return new AutoValue_Geometry.MoshiJsonAdapter(moshi);
    }

    public abstract Location location();
}
