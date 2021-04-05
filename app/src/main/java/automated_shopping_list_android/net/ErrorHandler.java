package automated_shopping_list_android.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import automated_shopping_list_android.Constants;
import retrofit2.Response;

public class ErrorHandler {

    public static String getServerError(Response<?> response) {
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            return jObjError.get("status") + ": " + jObjError.get("message");
        } catch (JSONException | IOException e) {
            return Constants.DEFAULT_ERROR;
        }
    }

}
