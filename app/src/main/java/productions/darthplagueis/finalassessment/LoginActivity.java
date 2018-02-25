package productions.darthplagueis.finalassessment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static productions.darthplagueis.finalassessment.Constants.DEFAULT_USER;
import static productions.darthplagueis.finalassessment.Constants.SHARED_PREFS;
import static productions.darthplagueis.finalassessment.Constants.USER_KEY;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String username;
    private EditText usernameText, passwordText;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkUser();

        setViews();

        setButtonOnClick();
    }

    private void checkUser() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = preferences.getString(USER_KEY, DEFAULT_USER);
        if (!username.equalsIgnoreCase(DEFAULT_USER)) {

        }
    }

    private void setViews() {
        usernameText = (EditText) findViewById(R.id.login_username);
        passwordText = (EditText) findViewById(R.id.login_password);
        submitBtn = (Button) findViewById(R.id.login_button);
    }

    private void setButtonOnClick() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameText.getText() == null || passwordText.getText() == null) {
                    if (usernameText.getText() == null) {
                        usernameText.setText(R.string.enter_username);
                    } else if (passwordText.getText() == null) {
                        passwordText.setText(R.string.enter_password);
                    }
                } else if (usernameText.getText() != null && passwordText.getText() != null) {
                    username = usernameText.getText().toString();
                    if (passwordText.getText().toString().toLowerCase().contains(username.toLowerCase())) {
                        passwordText.setText(R.string.contains_username);
                    } else {

                    }
                }
            }
        });
    }
}
