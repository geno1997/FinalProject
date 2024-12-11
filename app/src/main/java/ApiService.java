import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Call<ApiResponse> searchArticles(@Query("api-key") String apiKey, @Query("q") String query);
}
