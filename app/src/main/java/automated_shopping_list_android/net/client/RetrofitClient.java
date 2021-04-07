package automated_shopping_list_android.net.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import automated_shopping_list_android.Constants;
import automated_shopping_list_android.net.client.adapter.GsonLocalDateAdapter;
import automated_shopping_list_android.net.client.adapter.GsonLocalDateTimeAdapter;
import automated_shopping_list_android.net.client.interceptor.AuthInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new AuthInterceptor());
            OkHttpClient client = builder.build();

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter());
            gsonBuilder.registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter());
            Gson gson = gsonBuilder.create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
