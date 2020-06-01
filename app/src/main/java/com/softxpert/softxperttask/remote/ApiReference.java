package com.softxpert.softxperttask.remote;

import com.softxpert.softxperttask.remote.response.CarResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by µðšţãƒâ ™ on 01/06/2020.
 * ->
 */
public interface ApiReference {

    @GET("cars")
    Call<CarResponse> getCars(@Query("page") int page);
}
