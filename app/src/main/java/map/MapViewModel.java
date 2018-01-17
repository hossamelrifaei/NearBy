package map;

import com.google.android.gms.maps.model.LatLng;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.nearby.R;

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
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();


    @Inject
    MapViewModel() {

    }

    Observable<List<Address>> address() {
        errorRelay.accept(-1);
        return addressRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<List<Address>> addressUpdated() {
        return addressRelay;
    }


    Observable<LatLng> location() {
        return locationRelay;
    }

    Consumer<LatLng> locationUpdated() {
        errorRelay.accept(-1);
        return locationRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            errorRelay.accept(R.string.api_error_places);
        };
    }
}
