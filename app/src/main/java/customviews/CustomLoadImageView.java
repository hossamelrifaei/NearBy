package customviews;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by hossam on 1/16/18.
 */

public class CustomLoadImageView extends android.support.v7.widget.AppCompatImageView {

    public CustomLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void loadWithUrl(String url) {
        Transformation transformation = new CircleTransform();
        Picasso.with(getContext())
                .load(url)
                .transform(transformation)
                .priority(Picasso.Priority.LOW)
                .into(this);
    }

}
