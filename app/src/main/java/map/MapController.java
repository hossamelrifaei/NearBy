package map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.nearby.R;

import javax.inject.Inject;

import base.BaseController;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by hossam on 1/17/18.
 */

public class MapController extends BaseController implements OnMapInitializedListener {
    @Inject
    MapPresenter presenter;

    @Inject
    MapViewModel viewModel;

    @BindView(R.id.air_map_view)
    AirMapView mapView;

    @BindView(R.id.address_tv)
    TextView addressTv;

    public MapController(Bundle bundle) {
        super(bundle);
    }

    public static Controller newInstance() {
        Bundle bundle = new Bundle();
        return new MapController(bundle);
    }

    @OnClick(R.id.select_location_btn)
    public void onSelectClicked() {
        presenter.locationSelected();
    }

    @Override
    protected int layoutRes() {
        return R.layout.map_screen;
    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        if (changeType.isEnter) {
            mapView.setOnMapInitializedListener(this);
            mapView.initialize(((AppCompatActivity) getActivity()).getSupportFragmentManager());
        }
    }

    @Override
    public void onMapInitialized() {
        presenter.initMap();
        mapView.setOnCameraChangeListener(presenter);
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.address()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                    if (!data.isEmpty()) {
                        addressTv.setText(data.get(0).formattedAddress());
                    }
                }),
                viewModel.location()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(location -> {
                    mapView.setCenterZoom(location, 17);
                    presenter.getAddress(location);
                })
        };
    }
}
