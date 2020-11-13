package com.maro.kontripeople.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maro.kontripeople.util.ApiClient;
import com.maro.kontripeople.util.ApiInterface;
import com.maro.kontripeople.util.People;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleViewModel extends ViewModel {
    private MutableLiveData<List<People>> result;
    ApiInterface apiInterface;

    public LiveData<List<People>> getPeople(){
        if (result == null){
            result = new MutableLiveData<>();
            loadPeople();
        }
        return result;
    }

    private void loadPeople(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<People>> call = apiInterface.getPeople();
        call.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Log.i("Failure", "onFailure: " + t.toString());
            }
        });
    }

}
