package productions.darthplagueis.finalassessment.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import productions.darthplagueis.finalassessment.LoginActivity;

import static productions.darthplagueis.finalassessment.util.Constants.USER_KEY;

/**
 * Created by oleg on 2/25/18.
 */

public class SignOut {

    public static void logOutAndReturnToLogin(Context context, SharedPreferences preferences) {
        preferences.edit().remove(USER_KEY).apply();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
