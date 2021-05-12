package automated_shopping_list_android.net.model;

import com.google.gson.annotations.SerializedName;

public class ProductCount {

    @SerializedName("product")
    public Product product;
    @SerializedName("count")
    public Integer count;

}
