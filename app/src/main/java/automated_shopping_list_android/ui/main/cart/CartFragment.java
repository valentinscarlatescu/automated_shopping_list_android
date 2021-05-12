package automated_shopping_list_android.ui.main.cart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import automated_shopping_list_android.R;
import automated_shopping_list_android.net.client.RetrofitClient;
import automated_shopping_list_android.net.model.ProductCount;
import automated_shopping_list_android.net.service.ProductService;
import automated_shopping_list_android.ui.main.cart.home.CategoryCartsAdapter;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    @BindView(R.id.homeCartRecyclerView)
    RecyclerView recyclerView;

    @BindDrawable(R.drawable.vertical_separator)
    Drawable separator;


    private Unbinder unbinder;
    private CategoryCartsAdapter adapter = new CategoryCartsAdapter();
    private ProductService productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(separator);

        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);

        productService.getMostPopularProducts().enqueue(new Callback<List<ProductCount>>() {
            @Override
            public void onResponse(Call<List<ProductCount>> call, Response<List<ProductCount>> response) {
                if (response.isSuccessful()) {
                    List<ProductCount> productsCounts = response.body();
                    if (productsCounts != null) {
                        adapter.setData(productsCounts, 20);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductCount>> call, Throwable t) {

            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
