package productions.darthplagueis.finalassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static productions.darthplagueis.finalassessment.util.Constants.IMAGE_URL;
import static productions.darthplagueis.finalassessment.util.Constants.SHARED_PREFS;
import static productions.darthplagueis.finalassessment.util.SignOut.logOutAndReturnToLogin;
import static productions.darthplagueis.finalassessment.util.ToastMaker.createImageRetrievalToast;

public class PhotoActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Intent extras = getIntent();
        if (extras != null) {
            imageUrl = extras.getStringExtra(IMAGE_URL);
        }

        setViews();
    }

    private void setViews() {
        ImageView imageView = (ImageView) findViewById(R.id.photo_image);
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            createImageRetrievalToast(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                logOutAndReturnToLogin(this, preferences);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
