package map;

import com.google.android.gms.maps.model.LatLng;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import di.ScreenScope;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import model.Address;

/**
 * Created by hossam on 1/17/18.
 */
@ScreenScope
public class MapViewModel {
    private final BehaviorRelay<List<Address>> addressRelay = BehaviorRelay.create();
    private final BehaviorRelay<LatLng> locationRelay = BehaviorRelay.create();


    @Inject
    MapViewModel() {

    }

    Observable<List<Address>> address() {
        return addressRelay;
    }


    Consumer<List<Address>> addressUpdated() {
        return addressRelay;
    }

    Observable<LatLng> location() {
        return locationRelay;
    }

    Consumer<LatLng> locationUpdated() {
        return locationRelay;
    }
}
