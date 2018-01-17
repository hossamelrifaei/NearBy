package data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import model.Address;

/**
 * Created by hossam on 1/17/18.
 */

@AutoValue
public abstract class AddressResponse {
    public static JsonAdapter<AddressResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_AddressResponse.MoshiJsonAdapter(moshi);
    }

    public abstract List<Address> results();
}
