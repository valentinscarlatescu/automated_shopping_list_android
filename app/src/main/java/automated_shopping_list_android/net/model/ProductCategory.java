package automated_shopping_list_android.net.model;

import com.google.gson.annotations.SerializedName;

public class ProductCategory {

    @SerializedName("id")
    public Long id;
    @SerializedName("name")
    public String name;
    @SerializedName("imagePath")
    public String imagePath;

}
