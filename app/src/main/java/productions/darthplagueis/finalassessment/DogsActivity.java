package productions.darthplagueis.finalassessment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import productions.darthplagueis.finalassessment.network.RetrofitFactory;
import productions.darthplagueis.finalassessment.recyclerview.controller.DogsAdapter;

import static productions.darthplagueis.finalassessment.util.Constants.BREED_NAME;
import static productions.darthplagueis.finalassessment.util.Constants.SHARED_PREFS;
import static productions.darthplagueis.finalassessment.util.SignOut.logOutAndReturnToLogin;
import static productions.darthplagueis.finalassessment.util.ToastMaker.createNetworkErrorToast;

public class DogsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView dogBreedText;
    private String dogBreed;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Intent extras = getIntent();
        if (extras != null) {
            dogBreed = extras.getStringExtra(BREED_NAME);
        }

        setViews();

        getBreedImageList();
    }

    private void setViews() {
        dogBreedText = (TextView) findViewById(R.id.dog_breed);
        if (dogBreed != null) {
            dogBreedText.setText(dogBreed);
        }
        recyclerView = (RecyclerView) findViewById(R.id.dogs_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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

    private void getBreedImageList() {
        RetrofitFactory.SpecificImagesListener listener = new RetrofitFactory.SpecificImagesListener() {
            @Override
            public void specificImageListCallBack(List<String> responseList) {
                recyclerView.setAdapter(new DogsAdapter(responseList));
            }

            @Override
            public void onErrorCallBack() {
                createNetworkErrorToast(DogsActivity.this);
            }
        };
        RetrofitFactory.getInstance().setSpecificImagesListener(listener);
        RetrofitFactory.getInstance().getListOfBreedImages(dogBreed);
    }
}
