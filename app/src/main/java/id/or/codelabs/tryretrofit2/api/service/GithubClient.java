package id.or.codelabs.tryretrofit2.api.service;

import java.util.List;

import id.or.codelabs.tryretrofit2.api.model.GithubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mochadwi on 2/2/17.
 */

public interface GithubClient {
    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}
