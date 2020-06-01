package com.softxpert.softxperttask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softxpert.softxperttask.R;
import com.softxpert.softxperttask.model.Car;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by µðšţãƒâ ™ on 01/06/2020.
 * ->
 */
public class CarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_NORMAL = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean mIsLoaderVisible = false;
    private List<Car> mCars;

    public CarAdapter(List<Car> cars) {
        mCars = cars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NORMAL)
            return new CarHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false));
        else
            return new ProgressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_NORMAL) {
            CarHolder newHolder = (CarHolder) holder;
            newHolder.bindView(mCars.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (mIsLoaderVisible && position == mCars.size() - 1) ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    public void isLoaderVisible(boolean isLoaderVisible) {
        mIsLoaderVisible = isLoaderVisible;
    }

    class CarHolder extends RecyclerView.ViewHolder {
        private TextView brand;
        private TextView year;
        private TextView used;
        private CircleImageView carImage;

        CarHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.tvBrand);
            year = itemView.findViewById(R.id.tvYear);
            used = itemView.findViewById(R.id.tvNew);
            carImage = itemView.findViewById(R.id.civCarImage);
        }

        void bindView(Car item) {
            brand.setText(item.getBrand());
            year.setText(item.getConstructionYear());
            if (item.getUsed())
                used.setVisibility(View.VISIBLE);
            else
                used.setVisibility(View.INVISIBLE);

            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .into(carImage);
        }
    }

    class ProgressHolder extends RecyclerView.ViewHolder {
        public ProgressHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
