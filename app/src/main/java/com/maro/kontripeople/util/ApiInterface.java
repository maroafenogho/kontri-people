package com.maro.kontripeople.util;

import com.maro.kontripeople.fragments.AddPeopleFragment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/api/people")
    Call<List<People>> getPeople();

    @FormUrlEncoded
    @POST("/api/people")
    Call<ResponseBody> createPerson(
            @Field("name")String name,
            @Field("gender") AddPeopleFragment.Gender gender,
            @Field("occupation")String occupation,
            @Field("age")int age,
            @Field("employmentStatus")AddPeopleFragment.Employment employmentStatus,
            @Field("citizen")boolean citizen
    );
    @FormUrlEncoded
    @POST("/api/register")
    Call<ResponseBody> signUp(
            @Field("name")String name,
            @Field("email") String email,
            @Field("password")String password
    );
    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> login(
            @Field("email") String email,
            @Field("password")String password
    );
}
