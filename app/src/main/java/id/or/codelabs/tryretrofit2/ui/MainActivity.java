package id.or.codelabs.tryretrofit2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import id.or.codelabs.tryretrofit2.R;
import id.or.codelabs.tryretrofit2.api.model.GithubRepo;
import id.or.codelabs.tryretrofit2.api.service.GithubClient;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView v;
    private List<GithubRepo> repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adapter
        final String API_BASE_URL = "https://api.github.com";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder rb = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit r = rb.client(httpClient.build()).build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        GithubClient client =  r.create(GithubClient.class);

        // Fetch a list of the Github repositories.
        Call<List<GithubRepo>> call = client.reposForUser("mochadwi");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                repository = response.body();
                for (int i = 0; i < repository.size(); i++) {
                    Log.e("List " + i + 1 + ": ", repository.get(i).toString());
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}