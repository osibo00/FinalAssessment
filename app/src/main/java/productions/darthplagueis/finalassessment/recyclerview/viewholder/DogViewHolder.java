package productions.darthplagueis.finalassessment.recyclerview.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import productions.darthplagueis.finalassessment.PhotoActivity;
import productions.darthplagueis.finalassessment.R;

import static productions.darthplagueis.finalassessment.util.Constants.IMAGE_URL;

/**
 * Created by oleg on 2/25/18.
 */

public class DogViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public DogViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.item_view_image);
    }

    public void onBind(final String imageUrl) {
        Glide.with(itemView)
                .load(imageUrl)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoActivityIntent = new Intent(itemView.getContext(), PhotoActivity.class);
                photoActivityIntent.putExtra(IMAGE_URL, imageUrl);
                itemView.getContext().startActivity(photoActivityIntent);
            }
        });
    }
}
