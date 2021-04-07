package automated_shopping_list_android.ui.main.social;

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
import automated_shopping_list_android.net.model.User;
import automated_shopping_list_android.net.service.UserService;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.socialRecyclerView)
    RecyclerView recyclerView;

    @BindDrawable(R.drawable.vertical_separator)
    Drawable separator;


    private UsersAdapter usersAdapter = new UsersAdapter();

    private UserService userService = RetrofitClient.getRetrofitInstance().create(UserService.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        unbinder = ButterKnife.bind(this, view);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(separator);

        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(usersAdapter);

        userService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    usersAdapter.setUsers(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

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
