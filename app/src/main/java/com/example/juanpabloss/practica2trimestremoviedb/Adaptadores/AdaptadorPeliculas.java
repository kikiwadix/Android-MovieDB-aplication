package com.example.juanpabloss.practica2trimestremoviedb.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juanpabloss.practica2trimestremoviedb.Modelos.Pelicula;
import com.example.juanpabloss.practica2trimestremoviedb.R;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ImageDownloader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JuanPablo on 14/02/2017.
 */

public class AdaptadorPeliculas extends ArrayAdapter<Pelicula> {

    private LayoutInflater inflater;
    private List<Pelicula> listaPeliculas;


    public AdaptadorPeliculas(Context context, List<Pelicula> listaPeliculas) {

        super(context, R.layout.itempelicula, listaPeliculas);
        this.listaPeliculas = listaPeliculas;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(R.layout.itempelicula, null);

        new ViewHolder(convertView, listaPeliculas.get(position));

        return convertView;

    }


    static class ViewHolder {
        @BindView(R.id.textViewTitulo)
        TextView textViewTitulo;
        @BindView(R.id.textViewDescripcion)
        TextView textViewDescripcion;
        @BindView(R.id.caratula)
        ImageView caratula;
        @BindView(R.id.textViewValoracion)
        TextView textViewValoracion;
        @BindView(R.id.item_pelicula)
        RelativeLayout itemPelicula;

        private Pelicula pelicula;

        ViewHolder(View view, Pelicula pelicula) {
            ButterKnife.bind(this, view);

            this.pelicula = pelicula;
            this.textViewTitulo.setText(pelicula.getTitle());
            this.textViewDescripcion.setText(pelicula.getOverview());
            this.textViewValoracion.setText(pelicula.getVoteAverage().toString());

            new ImageDownloader(caratula).execute("https://image.tmdb.org/t/p/w300"+pelicula.getPosterPath());


        }



    }
}
