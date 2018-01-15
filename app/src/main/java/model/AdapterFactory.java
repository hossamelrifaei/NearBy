package model;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by hossam on 1/14/18.
 */

@MoshiAdapterFactory
public abstract class AdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_AdapterFactory();
    }
}

