package places;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearby.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import customviews.CustomLoadImageView;
import model.Place;
import utils.Utils;

/**
 * Created by hossam on 1/16/18.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private final List<Place> data = new ArrayList<>();

    PlacesAdapter() {
        setHasStableIds(true);
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void setData(List<Place> places) {
        if (places != null) {
            data.clear();
            data.addAll(places);

        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class PlaceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_tv)
        TextView placeNameText;
        @BindView(R.id.address_tv)
        TextView placeAddressText;
        @BindView(R.id.place_image)
        CustomLoadImageView placeImage;
        @BindView(R.id.fav_icon)
        ImageView favIcon;

        private Place place;

        PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Place place) {
            this.place = place;
            placeNameText.setText(place.name());
            placeAddressText.setText(place.address());
            placeImage.loadWithUrl(Utils.formatLocationImageUrl(place.geometry().location()));
        }
    }

}