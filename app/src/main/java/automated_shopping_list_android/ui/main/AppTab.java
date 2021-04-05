package automated_shopping_list_android.ui.main;

public enum AppTab {

    CART(0),
    SOCIAL(1),
    ADMIN(2),
    PROFILE(3);

    private int index;

    AppTab(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static AppTab getByIndex(int id) {
        switch (id) {
            default:
                return CART;
            case 1:
                return SOCIAL;
            case 2:
                return ADMIN;
            case 3:
                return PROFILE;
        }
    }

}
