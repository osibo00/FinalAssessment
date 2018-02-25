package productions.darthplagueis.finalassessment.recyclerview.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import productions.darthplagueis.finalassessment.R;
import productions.darthplagueis.finalassessment.recyclerview.viewholder.DogViewHolder;

/**
 * Created by oleg on 2/25/18.
 */

public class DogsAdapter extends RecyclerView.Adapter<DogViewHolder> {

    private List<String> imageUrlStringList;

    public DogsAdapter(List<String> imageUrlStringList) {
        this.imageUrlStringList = imageUrlStringList;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item_view, parent, false);
        return new DogViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.onBind(imageUrlStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageUrlStringList.size();
    }
}
