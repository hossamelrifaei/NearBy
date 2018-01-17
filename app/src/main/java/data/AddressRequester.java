package data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import model.Address;

/**
 * Created by hossam on 1/17/18.
 */

public class AddressRequester {
    private PlacesService placesService;

    @Inject
    AddressRequester(PlacesService placesService) {
        this.placesService = placesService;
    }

    public Single<List<Address>> getAddress(String location) {
        return placesService.getLocationAddress(location)
                .map(AddressResponse::results)
                .subscribeOn(Schedulers.io());
    }
}
