package productions.darthplagueis.finalassessment.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by oleg on 2/25/18.
 */

public class ToastMaker {

    public static void createNetworkErrorToast(Context context) {
        Toast.makeText(context, "Network error.", Toast.LENGTH_SHORT).show();
    }

    public static void createImageRetrievalToast(Context context) {
        Toast.makeText(context, "Cannot retrieve image.", Toast.LENGTH_SHORT).show();
    }
}
