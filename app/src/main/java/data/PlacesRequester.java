package data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import model.Place;

/**
 * Created by hossam on 1/15/18.
 */

public class PlacesRequester {
    private PlacesService placesService;

    @Inject
    PlacesRequester(PlacesService placesService) {
        this.placesService = placesService;
    }

    public Single<List<Place>> getPlaces(String location) {
        return placesService.getPlacesNearBy(location)
                .map(PlacesResponse::results)
                .subscribeOn(Schedulers.io());
    }
}
