package automated_shopping_list_android.ui.main.common.product;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import automated_shopping_list_android.R;
import automated_shopping_list_android.net.ImageHandler;
import automated_shopping_list_android.net.model.Product;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDialog extends Dialog {

    @BindView(R.id.dialogProfileCartProductImageView)
    ImageView imageView;
    @BindView(R.id.dialogProfileCartProductNameTextView)
    TextView nameTextView;
    @BindView(R.id.dialogProfileCartProductCategoryTextView)
    TextView categoryTextView;
    @BindView(R.id.dialogProfileCartProductPriceTextView)
    TextView priceTextView;
    @BindView(R.id.dialogProfileCartProductCartsNumberTextView)
    TextView cartsNumberTextView;

    @BindString(R.string.product_name)
    String productName;
    @BindString(R.string.product_category)
    String productCategory;
    @BindString(R.string.product_carts_number)
    String cartsNumber;

    private Product product;

    public ProductDialog(@NonNull Context context, Product product) {
        super(context);
        this.product = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_product);
        ButterKnife.bind(this);

        ImageHandler.loadImage(imageView, product.imagePath, getContext().getDrawable(R.drawable.item_placeholder_padding));
        nameTextView.setText(String.format(productName, product.name));

        categoryTextView.setText(String.format(productCategory, product.productCategory.name));
        priceTextView.setText(String.format(getContext().getString(product.quantityType.getName()), product.averagePrice));
        cartsNumberTextView.setText(String.format(cartsNumber, product.cartsNumber));

        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
