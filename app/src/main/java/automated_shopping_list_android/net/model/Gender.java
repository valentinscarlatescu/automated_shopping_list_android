package automated_shopping_list_android.net.model;

import androidx.annotation.StringRes;

import automated_shopping_list_android.R;

public enum Gender {

    MALE(R.string.gender_male), FEMALE(R.string.gender_female);

    @StringRes
    int name;

    Gender(@StringRes int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

}
