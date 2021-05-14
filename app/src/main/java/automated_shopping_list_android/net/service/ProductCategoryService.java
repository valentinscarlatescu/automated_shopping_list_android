package automated_shopping_list_android.net.service;

import java.util.List;

import automated_shopping_list_android.net.model.ProductCategory;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductCategoryService {

    @GET("/api/productCategories")
    Call<List<ProductCategory>> getAll();

}
