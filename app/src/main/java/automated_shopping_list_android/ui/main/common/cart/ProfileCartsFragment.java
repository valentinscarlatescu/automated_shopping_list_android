package automated_shopping_list_android.ui.main.common.cart;

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
import automated_shopping_list_android.net.model.Cart;
import automated_shopping_list_android.ui.main.MainActivity;
import automated_shopping_list_android.ui.main.common.product.ProfileCartProductsFragment;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileCartsFragment extends Fragment implements ProfileCartsAdapter.CartClickListener {

    @BindView(R.id.profileCartsRecyclerView)
    RecyclerView recyclerView;

    @BindDrawable(R.drawable.vertical_separator)
    Drawable separator;

    private Unbinder unbinder;
    private List<Cart> carts;
    private ProfileCartsAdapter cartsAdapter = new ProfileCartsAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_carts, container, false);
        unbinder = ButterKnife.bind(this, view);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(separator);

        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(cartsAdapter);

        cartsAdapter.setCarts(carts);
        cartsAdapter.setOnCartClickListener(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public void onCartClicked(Cart cart) {
        ProfileCartProductsFragment fragment = new ProfileCartProductsFragment();
        fragment.setProducts(cart.cartProducts);
        ((MainActivity) requireActivity()).setFragment(fragment);
    }

}
