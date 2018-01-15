package places;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.nearby.R;

import javax.inject.Inject;

import base.BaseController;
import base.Constants;
import home.OnPermissionListener;

/**
 * Created by hossam on 1/14/18.
 */

public class PlacesController extends BaseController implements OnPermissionListener {

    @Inject
    PlacesViewModel viewModel;
    @Inject
    PlacePresenter presenter;

    @Override
    protected int layoutRes() {
        return R.layout.screen_places_list;
    }

    @Override
    protected void initPresenter() {
        presenter.setPermissionListener(this);
        presenter.start();
    }

    @Override
    public boolean HasPermission() {
        return ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    presenter.start();

                }
            }

        }
    }
}
