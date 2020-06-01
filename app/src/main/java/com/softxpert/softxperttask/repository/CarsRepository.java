package com.softxpert.softxperttask.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softxpert.softxperttask.model.Car;
import com.softxpert.softxperttask.remote.ApiReference;
import com.softxpert.softxperttask.remote.RetrofitClientInstance;
import com.softxpert.softxperttask.remote.response.CarResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by µðšţãƒâ ™ on 01/06/2020.
 * ->
 */
public class CarsRepository {
    private MutableLiveData<List<Car>> mCars;
    private ApiReference mApiReference;

    public CarsRepository() {
        mApiReference = RetrofitClientInstance.getRetrofitInstance().create(ApiReference.class);
    }

    public LiveData<List<Car>> getCars(int page) {
        mCars = new MutableLiveData<>();

        mApiReference.getCars(page).enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                if (response.body() != null) {
                    int status = response.body().status;
                    if (status == 1)
                        mCars.setValue(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return mCars;
    }
}
