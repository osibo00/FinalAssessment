package productions.darthplagueis.finalassessment.network;

import productions.darthplagueis.finalassessment.model.RandomBreed;
import productions.darthplagueis.finalassessment.model.SpecificBreed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by oleg on 2/25/18.
 */

public interface DogCeoService {

    String randomBreedImageEndpoint = "breed/{breed-name}/images/random";
    String specificBreedImagesEndpoint = "breed/{breed-name}/images";

    @GET(randomBreedImageEndpoint)
    Call<RandomBreed> getRandomBreedImage(@Path("breed-name") String breed);

    @GET(specificBreedImagesEndpoint)
    Call<SpecificBreed> getSpecificBreedImages(@Path("breed-name") String breed);
}
