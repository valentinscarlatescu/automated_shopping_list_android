package automated_shopping_list_android.net.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Set;

public class Cart {

    @SerializedName("id")
    public Long id;
    @SerializedName("user")
    public User user;
    @SerializedName("cartProducts")
    public Set<Product> cartProducts;
    @SerializedName("dateTime")
    public LocalDateTime dateTime;
    @SerializedName("quantity")
    public Long quantity;

}
