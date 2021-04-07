package automated_shopping_list_android.ui.main;

import android.os.Bundle;
import android.util.ArrayMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import automated_shopping_list_android.R;
import automated_shopping_list_android.ui.main.cart.CartTabFragment;
import automated_shopping_list_android.ui.main.profile.ProfileTabFragment;
import automated_shopping_list_android.ui.main.social.SocialTabFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AppTab appTab = AppTab.CART;

    private Map<AppTab, TabFragment> fragments = new ArrayMap<>(4);

    {
        fragments.put(AppTab.CART, new CartTabFragment());
        fragments.put(AppTab.SOCIAL, new SocialTabFragment());
        fragments.put(AppTab.PROFILE, new ProfileTabFragment());
    }

    @BindView(R.id.mainBottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.mainMenuCart:
                    if (appTab != AppTab.CART) {
                        openTab(AppTab.CART);
                        appTab = AppTab.CART;
                    }
                    break;
                case R.id.mainMenuSocial:
                    if (appTab != AppTab.SOCIAL) {
                        openTab(AppTab.SOCIAL);
                        appTab = AppTab.SOCIAL;
                    }
                    break;
                case R.id.mainMenuProfile:
                    if (appTab != AppTab.PROFILE) {
                        openTab(AppTab.PROFILE);
                        appTab = AppTab.PROFILE;
                    }
                    break;
            }
            return true;
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Stream.of(AppTab.values()).forEach(o -> {
            Fragment fragment = fragments.get(o);
            String tag = String.valueOf(o.getIndex());
            if (fragment != null) {
                ft.add(R.id.mainContent, fragment, tag);
                ft.hide(fragment);
            }
        });

        Fragment activeFragment = fragments.get(appTab);
        if (activeFragment != null) {
            ft.show(activeFragment);
            ft.commit();
        }
    }

    public void setFragment(Fragment fragment) {
        for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
            Fragment tabFragment = getSupportFragmentManager().getFragments().get(i);
            if (tabFragment.isVisible()) {
                ((TabFragment) tabFragment).setFragment(fragment);
                return;
            }
        }
    }

    private void openTab(AppTab appTab) {
        TabFragment fragmentToHide = fragments.get(this.appTab);
        TabFragment fragmentToShow = fragments.get(appTab);

        Objects.requireNonNull(fragmentToHide);
        Objects.requireNonNull(fragmentToShow);

        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragmentToHide)
                .show(fragmentToShow)
                .commit();

        fragmentToShow.onTabClicked();
    }
}
