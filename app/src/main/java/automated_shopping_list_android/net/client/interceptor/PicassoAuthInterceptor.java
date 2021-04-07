package automated_shopping_list_android.net.client.interceptor;

import androidx.annotation.NonNull;

import com.auth0.android.jwt.JWT;

import java.io.IOException;

import automated_shopping_list_android.net.Session;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class PicassoAuthInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request mainRequest = chain.request();
        Session session = Session.getInstance();
        JWT token = new JWT(session.getToken());

        if (!token.isExpired(1)) {
            Request modifiedRequest = mainRequest.newBuilder()
                    .addHeader("Authorization", "Bearer " + session.getToken())
                    .build();
            return chain.proceed(modifiedRequest);
        }

        return chain.proceed(mainRequest);
    }

}
