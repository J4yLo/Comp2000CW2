package com.example.comp2000cw2;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class NetworkClient {

        private static Retrofit retrofit;
        private static String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/";

        public static Retrofit getRetrofit() {
            OkHttpClient httpClient = new OkHttpClient.Builder().build();
            if (retrofit == null) {
                retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(httpClient).build();
            }
            return retrofit;
        }

    }

