package com.example.actionbar;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ImageService {



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/v1/models/inceptionV3:predict")
    Call<JsonObject> upload(@Body JsonObject base64);
}