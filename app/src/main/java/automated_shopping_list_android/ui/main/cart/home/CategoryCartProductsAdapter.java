package automated_shopping_list_android.ui.main.cart.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import automated_shopping_list_android.R;
import automated_shopping_list_android.net.ImageHandler;
import automated_shopping_list_android.net.model.ProductCount;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryCartProductsAdapter extends RecyclerView.Adapter<CategoryCartProductsAdapter.CategoryCartProductViewHolder> {

    private List<ProductCount> productsCounts;

    @NonNull
    @Override
    public CategoryCartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
        return new CategoryCartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCartProductViewHolder holder, int position) {
        ProductCount productCount = productsCounts.get(position);

        ImageHandler.loadImage(holder.photoImageView, productCount.product.imagePath, holder.itemView.getContext().getDrawable(R.drawable.item_placeholder_padding));
        holder.productTextView.setText(productCount.product.name);

        holder.countTextView.setText(String.valueOf(productCount.count));
    }

    @Override
    public int getItemCount() {
        return productsCounts == null ? 0 : productsCounts.size();
    }

    public void setProducts(List<ProductCount> productsCounts) {
        this.productsCounts = productsCounts;
    }

    class CategoryCartProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemCartProductImageView)
        ImageView photoImageView;
        @BindView(R.id.itemCartProductTextView)
        TextView productTextView;
        @BindView(R.id.itemCartProductCountTextView)
        TextView countTextView;

        public CategoryCartProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
