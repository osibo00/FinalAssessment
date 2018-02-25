package productions.darthplagueis.finalassessment.network;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import productions.darthplagueis.finalassessment.abstractclasses.AbstractRetrofitFactory;
import productions.darthplagueis.finalassessment.model.RandomBreed;
import productions.darthplagueis.finalassessment.model.SpecificBreed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oleg on 2/25/18.
 */

public class RetrofitFactory extends AbstractRetrofitFactory {

    public static final String TAG = "RetrofitFactory";
    private static RetrofitFactory retrofitFactory;

    private RandomImageListener randomImageListener = null;
    private SpecificImagesListener specificImagesListener = null;

    @NonNull
    public static RetrofitFactory getInstance() {
        if (retrofitFactory == null) {
            retrofitFactory = new RetrofitFactory();
        }
        return retrofitFactory;
    }

    private RetrofitFactory(){}

    public void setRandomImageListener(RandomImageListener randomImageListener) {
        this.randomImageListener = randomImageListener;
    }

    public void setSpecificImagesListener(SpecificImagesListener specificImagesListener) {
        this.specificImagesListener = specificImagesListener;
    }

    public void getBreedImage(String breed) {
        DogCeoService service = buildRetrofit().create(DogCeoService.class);
        Call<RandomBreed> call = service.getRandomBreedImage(breed);
        call.enqueue(new Callback<RandomBreed>() {
            @Override
            public void onResponse(Call<RandomBreed> call, Response<RandomBreed> response) {
                if (response.isSuccessful()) {
                    randomImageListener.randomImageCallBack(response.body().getMessage());
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RandomBreed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                randomImageListener.onErrorCallBack();
            }
        });
    }

    public void getListOfBreedImages(String breed) {
        DogCeoService service = buildRetrofit().create(DogCeoService.class);
        Call<SpecificBreed> call = service.getSpecificBreedImages(breed);
        call.enqueue(new Callback<SpecificBreed>() {
            @Override
            public void onResponse(Call<SpecificBreed> call, Response<SpecificBreed> response) {
                if (response.isSuccessful()) {
                    List<String> imageList = response.body().getMessage();
                    specificImagesListener.specificImageListCallBack(imageList);
                    Log.d(TAG, "onResponse: listSize=" + imageList.size());
                    Log.d(TAG, "onResponse: " + imageList.get(0));
                }
            }

            @Override
            public void onFailure(Call<SpecificBreed> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                specificImagesListener.onErrorCallBack();
            }
        });
    }

    @Override
    public String getHostUrl() {
        return "https://dog.ceo/api/";
    }

    public interface RandomImageListener {
        void randomImageCallBack(String imageUrl);
        void onErrorCallBack();
    }

    public interface SpecificImagesListener {
        void specificImageListCallBack(List<String> responseList);
        void onErrorCallBack();
    }
}
