package automated_shopping_list_android.net.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    public Long id;
    @SerializedName("productCategory")
    public ProductCategory productCategory;
    @SerializedName("name")
    public String name;
    @SerializedName("averagePrice")
    public Integer averagePrice;
    @SerializedName("quantityType")
    public QuantityType quantityType;
    @SerializedName("imagePath")
    public String imagePath;
    @SerializedName("cartsNumber")
    public int cartsNumber;

}
