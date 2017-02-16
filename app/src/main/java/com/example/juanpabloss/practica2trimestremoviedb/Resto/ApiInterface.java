package com.example.juanpabloss.practica2trimestremoviedb.Resto;


import com.example.juanpabloss.practica2trimestremoviedb.Modelos.PeliculasResponse;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.RepartoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<PeliculasResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<PeliculasResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<PeliculasResponse> getActorMovies(@Query("with_crew") int id, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<PeliculasResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/credits")
    Call<RepartoResponse> getReparto(@Path("id") int id, @Query("api_key") String apiKey);


}
