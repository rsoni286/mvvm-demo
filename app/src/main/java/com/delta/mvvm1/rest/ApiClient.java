package com.delta.mvvm1.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://app.hiredevelopernow.com/api/";
    private static SBAppInterface sbAppInterface;

    public static SBAppInterface getSbAppInterface() {
        if (sbAppInterface == null) {
            OkHttpClient.Builder okClient = new OkHttpClient.Builder();
            okClient.connectTimeout(60000, TimeUnit.MILLISECONDS);
            okClient.writeTimeout(60000, TimeUnit.MILLISECONDS);
            okClient.readTimeout(60000, TimeUnit.MILLISECONDS);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);

            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    response.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build();
                    return response;
                }
            });

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            sbAppInterface = client.create(SBAppInterface.class);
        }
        return sbAppInterface;

    }


}