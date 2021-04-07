package automated_shopping_list_android.net.service;

import automated_shopping_list_android.net.model.Token;
import automated_shopping_list_android.net.model.User;
import automated_shopping_list_android.net.model.body.AuthLoginBody;
import automated_shopping_list_android.net.model.body.AuthRegisterBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/api/auth/register")
    Call<User> register(@Body AuthRegisterBody body);

    @POST("/api/auth/login")
    Call<Token> login(@Body AuthLoginBody body);

    @POST("/api/auth/logout")
    Call<Void> logout();

}
