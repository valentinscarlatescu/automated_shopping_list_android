package automated_shopping_list_android.net.service;

import java.util.List;

import automated_shopping_list_android.net.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface UserService {

    @PUT("/api/profile")
    Call<User> updateProfile(@Body User user);

    @GET("/api/users")
    Call<List<User>> getUsers();

}
