package automated_shopping_list_android.ui.main.cart.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import automated_shopping_list_android.R;
import automated_shopping_list_android.net.model.ProductCategory;
import automated_shopping_list_android.net.model.ProductCount;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryCartsAdapter extends RecyclerView.Adapter<CategoryCartsAdapter.CategoryCartViewHolder> {

    private List<ProductCount> productsCounts;
    private List<ProductCategory> categories;
    private int productsLimit;

    @NonNull
    @Override
    public CategoryCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_cart, parent, false);
        return new CategoryCartsAdapter.CategoryCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCartViewHolder holder, int position) {
        ProductCategory category = categories.get(position);

        if (category.id == null) {
            holder.categoryTextView.setText(R.string.most_popular_products);
        } else {
            holder.categoryTextView.setText(category.name);
        }

        CategoryCartProductsAdapter productsAdapter = new CategoryCartProductsAdapter();
        holder.categoryRecyclerView.setAdapter(productsAdapter);

        if (category.name != null) {
            List<ProductCount> categoryProducts = productsCounts.stream()
                    .filter(productCount -> productCount.product.productCategory.name.equals(category.name))
                    .limit(productsLimit)
                    .collect(Collectors.toList());
            productsAdapter.setProducts(categoryProducts);
        } else {
            List<ProductCount> allProducts = productsCounts.stream()
                    .limit(productsLimit)
                    .collect(Collectors.toList());
            productsAdapter.setProducts(allProducts);
        }

    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public void setData(List<ProductCount> productsCounts, int productsLimit) {
        this.productsLimit = productsLimit;

        this.productsCounts = productsCounts;

        ProductCategory newCategory = new ProductCategory();
        this.categories = new ArrayList<>();
        this.categories.add(newCategory);

        List<ProductCategory> realCategories = productsCounts.stream()
                .map(productCount -> productCount.product.productCategory)
                .distinct()
                .collect(Collectors.toList());

        this.categories.addAll(realCategories);
    }

    class CategoryCartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemCartCategoryProductsTextView)
        TextView categoryTextView;
        @BindView(R.id.itemCartCategoryProductsRecyclerView)
        RecyclerView categoryRecyclerView;

        public CategoryCartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
