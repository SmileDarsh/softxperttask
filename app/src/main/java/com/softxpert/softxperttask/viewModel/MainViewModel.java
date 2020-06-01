package com.softxpert.softxperttask.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.softxpert.softxperttask.model.Car;
import com.softxpert.softxperttask.repository.CarsRepository;

import java.util.List;

/**
 * Created by µðšţãƒâ ™ on 01/06/2020.
 * ->
 */
public class MainViewModel extends ViewModel {
    private LiveData<List<Car>> mCarLiveData;

    public MainViewModel() {
        CarsRepository mCarsRepository = new CarsRepository();
        mCarLiveData = mCarsRepository.getCars(1);
    }

    public LiveData<List<Car>> getCarsData() {
        return mCarLiveData;
    }
}
