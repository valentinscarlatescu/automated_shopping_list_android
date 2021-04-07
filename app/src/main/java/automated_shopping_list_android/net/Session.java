package automated_shopping_list_android.net;

import android.os.Handler;

import automated_shopping_list_android.net.model.User;

public class Session {

    private static Session session;

    private Handler handler = new Handler();
    private User user;
    private String token;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }
}

