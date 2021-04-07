package automated_shopping_list_android;

import android.app.Application;

import com.squareup.picasso.Picasso;

import automated_shopping_list_android.net.client.PicassoClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(PicassoClient.getPicasso(this));
    }

}
