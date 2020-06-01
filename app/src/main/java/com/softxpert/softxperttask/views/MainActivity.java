package com.softxpert.softxperttask.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.softxpert.softxperttask.R;
import com.softxpert.softxperttask.adapter.CarAdapter;
import com.softxpert.softxperttask.model.Car;
import com.softxpert.softxperttask.utils.PaginationListener;
import com.softxpert.softxperttask.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private ProgressBar mProgress;
    private RecyclerView mRvCars;
    private List<Car> mCars = new ArrayList<>();
    private CarAdapter mAdapter;
    private int mPage = 1;
    private boolean mIsLoader = false;
    private boolean mIsLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = findViewById(R.id.pbProgress);
        mRvCars = findViewById(R.id.rvCars);

        initCarRecycler();

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getCarsData().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                mProgress.setVisibility(View.GONE);
                mCars.addAll(cars);
                mAdapter.notifyDataSetChanged();
                mIsLoader = false;
            }
        });
    }

    private void initCarRecycler() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CarAdapter(mCars);
        mRvCars.setHasFixedSize(true);
        mRvCars.setLayoutManager(mLayoutManager);
        mRvCars.setAdapter(mAdapter);

        mRvCars.addOnScrollListener(new PaginationListener(mLayoutManager) {
            @Override
            public void loadMoreItems() {
                mIsLoader = true;
                mAdapter.isLoaderVisible(mIsLoader);
                mPage++;
            }

            @Override
            public Boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public Boolean isLoading() {
                return mIsLoader;
            }
        });
    }
}
