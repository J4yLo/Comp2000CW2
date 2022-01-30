package com.example.comp2000cw2;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UploadImg {
    @Multipart
    @POST("{id}/image")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part part, @Path("id") int UserID);
}
