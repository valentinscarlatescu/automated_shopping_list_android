package automated_shopping_list_android.net.service;

import java.util.List;

import automated_shopping_list_android.net.model.Cart;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CartService {

    @GET("/api/carts")
    Call<List<Cart>> getByUserId(@Query("userId") Long userId);

}
