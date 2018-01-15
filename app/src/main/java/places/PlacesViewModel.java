package places;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.nearby.R;

import java.util.List;

import javax.inject.Inject;

import di.ScreenScope;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import model.Place;

/**
 * Created by hossam on 1/15/18.
 */
@ScreenScope
public class PlacesViewModel {
    private static final int ERROR_CODE = -1;
    private final BehaviorRelay<List<Place>> placesRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    PlacesViewModel() {

    }

    Observable<List<Place>> places() {
        return placesRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Consumer<List<Place>> placesUpdated() {
        errorRelay.accept(ERROR_CODE);
        return placesRelay;
    }

    Consumer<Integer> errorUpdated() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            errorRelay.accept(R.string.api_error_places);
        };
    }

}
