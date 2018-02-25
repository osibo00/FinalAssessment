package productions.darthplagueis.finalassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import productions.darthplagueis.finalassessment.network.RetrofitFactory;

import static productions.darthplagueis.finalassessment.util.Constants.BREED_NAME;
import static productions.darthplagueis.finalassessment.util.Constants.DEFAULT_USER;
import static productions.darthplagueis.finalassessment.util.Constants.POODLE;
import static productions.darthplagueis.finalassessment.util.Constants.RETRIEVER;
import static productions.darthplagueis.finalassessment.util.Constants.SHARED_PREFS;
import static productions.darthplagueis.finalassessment.util.Constants.SPANIEL;
import static productions.darthplagueis.finalassessment.util.Constants.TERRIER;
import static productions.darthplagueis.finalassessment.util.Constants.USER_KEY;
import static productions.darthplagueis.finalassessment.util.SignOut.logOutAndReturnToLogin;
import static productions.darthplagueis.finalassessment.util.ToastMaker.createNetworkErrorToast;

public class BreedsActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private TextView usernameText;
    private String username;
    private ImageView terrierImage, spanielImage, retrieverImage, poodleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        checkUser();

        setViews();

        getBreedImages();
    }

    private void checkUser() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = preferences.getString(USER_KEY, DEFAULT_USER);
        if (username.equalsIgnoreCase(DEFAULT_USER)) {
            startActivity(new Intent(BreedsActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void setViews() {
        usernameText = (TextView) findViewById(R.id.breeds_username);
        usernameText.setText(String.format("%s%s?", getString(R.string.what_kind_dog), username));
        terrierImage = (ImageView) findViewById(R.id.terrier_image);
        spanielImage = (ImageView) findViewById(R.id.spaniel_image);
        retrieverImage = (ImageView) findViewById(R.id.retriever_image);
        poodleImage = (ImageView) findViewById(R.id.poodle_image);

        findViewById(R.id.terrier_card).setOnClickListener(this);
        findViewById(R.id.spaniel_card).setOnClickListener(this);
        findViewById(R.id.retriever_card).setOnClickListener(this);
        findViewById(R.id.poodle_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.terrier_card:
                startDogsActivity(TERRIER);
                break;
            case R.id.spaniel_card:
                startDogsActivity(SPANIEL);
                break;
            case R.id.retriever_card:
                startDogsActivity(RETRIEVER);
                break;
            case R.id.poodle_card:
                startDogsActivity(POODLE);
                break;
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

    private void getBreedImages() {
        RetrofitFactory.RandomImageListener terrierListener = new RetrofitFactory.RandomImageListener() {
            @Override
            public void randomImageCallBack(String imageUrl) {
                Glide.with(BreedsActivity.this)
                        .load(imageUrl)
                        .into(terrierImage);
                setSpanielImage();
            }

            @Override
            public void onErrorCallBack() {
                createNetworkErrorToast(BreedsActivity.this);
            }
        };
        RetrofitFactory.getInstance().setRandomImageListener(terrierListener);
        RetrofitFactory.getInstance().getBreedImage(TERRIER);
    }

    private void setSpanielImage() {
        RetrofitFactory.RandomImageListener spanielListener = new RetrofitFactory.RandomImageListener() {
            @Override
            public void randomImageCallBack(String imageUrl) {
                Glide.with(BreedsActivity.this)
                        .load(imageUrl)
                        .into(spanielImage);
                setRetrieverImage();
            }

            @Override
            public void onErrorCallBack() {
                createNetworkErrorToast(BreedsActivity.this);
            }
        };
        RetrofitFactory.getInstance().setRandomImageListener(spanielListener);
        RetrofitFactory.getInstance().getBreedImage(SPANIEL);
    }

    private void setRetrieverImage() {
        RetrofitFactory.RandomImageListener retrieverListener = new RetrofitFactory.RandomImageListener() {
            @Override
            public void randomImageCallBack(String imageUrl) {
                Glide.with(BreedsActivity.this)
                        .load(imageUrl)
                        .into(retrieverImage);
                setPoodleImage();
            }

            @Override
            public void onErrorCallBack() {
                createNetworkErrorToast(BreedsActivity.this);
            }
        };
        RetrofitFactory.getInstance().setRandomImageListener(retrieverListener);
        RetrofitFactory.getInstance().getBreedImage(RETRIEVER);
    }

    private void setPoodleImage() {
        RetrofitFactory.RandomImageListener poodleListener = new RetrofitFactory.RandomImageListener() {
            @Override
            public void randomImageCallBack(String imageUrl) {
                Glide.with(BreedsActivity.this)
                        .load(imageUrl)
                        .into(poodleImage);
            }

            @Override
            public void onErrorCallBack() {
                createNetworkErrorToast(BreedsActivity.this);
            }
        };
        RetrofitFactory.getInstance().setRandomImageListener(poodleListener);
        RetrofitFactory.getInstance().getBreedImage(POODLE);
    }

    private void startDogsActivity(String breed) {
        Intent dogsActivityIntent = new Intent(BreedsActivity.this, DogsActivity.class);
        dogsActivityIntent.putExtra(BREED_NAME, breed);
        startActivity(dogsActivityIntent);
    }
}


