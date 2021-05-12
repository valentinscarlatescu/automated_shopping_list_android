package automated_shopping_list_android.net.service;

import java.util.List;

import automated_shopping_list_android.net.model.ProductCount;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/api/products/mostPopular")
    Call<List<ProductCount>> getMostPopularProducts();

}
