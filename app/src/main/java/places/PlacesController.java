package places;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nearby.R;

import javax.inject.Inject;

import base.BaseController;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by hossam on 1/14/18.
 */

public class PlacesController extends BaseController {
    @Inject
    PlacesViewModel viewModel;
    @Inject
    PlacePresenter presenter;
    @BindView(R.id.places_list)
    RecyclerView placesList;
    @BindView(R.id.loading_indicator)
    View loadingView;
    @BindView(R.id.tv_error)
    TextView errorTextView;

    @Override
    protected int layoutRes() {
        return R.layout.screen_places_list;
    }

    @Override
    protected void initPresenter() {
        presenter.start();
    }

    @OnClick(R.id.fab)
    public void fabClicked() {
        presenter.openMap();
    }

    @Override
    protected void onViewBound(View view) {
        placesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        placesList.setAdapter(new PlacesAdapter());

    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    placesList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorTextView.setVisibility(loading ? View.GONE : errorTextView.getVisibility());
                }),
                viewModel.places()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(((PlacesAdapter) placesList.getAdapter())::setData),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorTextView.setText(null);
                        errorTextView.setVisibility(View.GONE);
                    } else {
                        errorTextView.setVisibility(View.VISIBLE);
                        placesList.setVisibility(View.GONE);
                        errorTextView.setText(errorRes);
                    }
                })
        };
    }

}
