package automated_shopping_list_android.net;

import android.os.Handler;

import automated_shopping_list_android.net.model.User;

public class Session {

    private static Session session;

    private android.os.Handler handler = new Handler();

    private User user;

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Session.session = session;
    }

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

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }
}

