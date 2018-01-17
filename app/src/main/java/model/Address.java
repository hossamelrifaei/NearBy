package model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by hossam on 1/17/18.
 */

@AutoValue
public abstract class Address {
    public static JsonAdapter<Address> jsonAdapter(Moshi moshi) {
        return new AutoValue_Address.MoshiJsonAdapter(moshi);
    }

    @Json(name = "formatted_address")
    public abstract String formattedAddress();
}
