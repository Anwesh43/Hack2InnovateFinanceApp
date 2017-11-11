package present.hack2innovate.demo.hack2innovatedemoapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class RetrofitSingleton {
    private static Retrofit localRetrofit = new Retrofit.Builder().baseUrl("http://172.16.14.245:8000/").addConverterFactory(GsonConverterFactory.create()).build();
    public static Retrofit getInstance() {
        return localRetrofit;
    }
}
