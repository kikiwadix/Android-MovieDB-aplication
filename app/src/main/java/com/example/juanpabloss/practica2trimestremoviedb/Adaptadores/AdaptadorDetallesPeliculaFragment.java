package com.example.juanpabloss.practica2trimestremoviedb.Adaptadores;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.juanpabloss.practica2trimestremoviedb.Actividades.MainActivity;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.Cast;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.Pelicula;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.RepartoResponse;
import com.example.juanpabloss.practica2trimestremoviedb.R;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ApiClient;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ApiInterface;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ImageDownloader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JuanPablo on 14/02/2017.
 */

public class AdaptadorDetallesPeliculaFragment extends Fragment {

    private ListView listView;
    public Pelicula pelicula;


    //he creado el constructor, paa intentar no utilizar el bundle
    public AdaptadorDetallesPeliculaFragment(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_peliculas_detalles, container, false);
        this.listView = (ListView) view.findViewById(R.id.lvReparto);

        ViewHolder viewhol = new ViewHolder(view);

        return view;
    }


    class ViewHolder {
        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.titulo)
        TextView titulo;
        @BindView(R.id.puntuacion)
        TextView puntuacion;
        @BindView(R.id.descripcion)
        TextView descripcion;
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.lvReparto)
        ListView lvReparto;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

            /*
            this.titulo.setText(getArguments().getString("titulo"));
            this.descripcion.setText(getArguments().getString("descripcion"));
            this.puntuacion.setText(getArguments().getString("valoracion"));

            new ImageDownloader(poster).execute("https://image.tmdb.org/t/p/w300"+ getArguments().getString("caratula"));
            */


            //sin bundle, porque me daba problemas
            this.titulo.setText(pelicula.getTitle());
            this.descripcion.setText(pelicula.getOverview());
            this.puntuacion.setText(pelicula.getVoteAverage().toString());

            new ImageDownloader(poster).execute("https://image.tmdb.org/t/p/w300"+ pelicula.getPosterPath());



            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<RepartoResponse> call = apiService.getReparto( pelicula.getId(), MainActivity.API_KEY);



            call.enqueue(new Callback<RepartoResponse>() {
                @Override
                public void onResponse(Call<RepartoResponse> call, Response<RepartoResponse> response) {

                    List<Cast> listaReparto = response.body().getCast();
                    AdaptadorReparto adaptadorReparto = new AdaptadorReparto(getContext(), listaReparto);
                    lvReparto.setAdapter(adaptadorReparto);
                }

                @Override
                public void onFailure(Call<RepartoResponse> call, Throwable t) {

                }
            });


        }
    }
}
