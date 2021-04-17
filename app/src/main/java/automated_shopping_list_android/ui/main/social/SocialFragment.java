package automated_shopping_list_android.ui.main.social;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

import automated_shopping_list_android.R;
import automated_shopping_list_android.net.client.RetrofitClient;
import automated_shopping_list_android.net.model.User;
import automated_shopping_list_android.net.service.UserService;
import automated_shopping_list_android.ui.main.MainActivity;
import automated_shopping_list_android.ui.main.social.profile.SocialProfileFragment;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialFragment extends Fragment implements UsersAdapter.UserClickListener {

    @BindView(R.id.socialSearchView)
    SearchView searchView;
    @BindView(R.id.socialRecyclerView)
    RecyclerView recyclerView;

    @BindDrawable(R.drawable.vertical_separator)
    Drawable separator;

    private Unbinder unbinder;
    private List<User> users;
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
                    SocialFragment.this.users = response.body();
                    usersAdapter.setUsers(SocialFragment.this.users);
                    usersAdapter.setOnUserClickListener(SocialFragment.this);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.isEmpty()) {
                    usersAdapter.setUsers(users);
                } else {
                    List<User> newUsers = users.stream()
                            .filter(user -> user.firstName != null && user.lastName != null &&
                                    (user.firstName.toLowerCase().contains(newText.toLowerCase()) ||
                                            user.lastName.toLowerCase().contains(newText.toLowerCase())))
                            .collect(Collectors.toList());
                    usersAdapter.setUsers(newUsers);
                }
                usersAdapter.notifyDataSetChanged();
                return true;
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onUserClicked(User user) {
        SocialProfileFragment fragment = new SocialProfileFragment();
        fragment.setUser(user);
        ((MainActivity) requireActivity()).setFragment(fragment);
    }

}
