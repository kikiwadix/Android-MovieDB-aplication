package com.example.juanpabloss.practica2trimestremoviedb.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juanpabloss.practica2trimestremoviedb.Modelos.Cast;
import com.example.juanpabloss.practica2trimestremoviedb.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JuanPablo on 14/02/2017.
 */

public class AdaptadorReparto extends ArrayAdapter<Cast> {

    private LayoutInflater inflater;
    private List<Cast> listaReparto;


    public AdaptadorReparto(Context context, List<Cast> reparto) {
        super(context, R.layout.itemreparto, reparto);
        this.listaReparto = reparto;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(R.layout.itemreparto, null);

        new ViewHolder(convertView, listaReparto.get(position));

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tvNombreActor)
        TextView tvNombreActor;
        @BindView(R.id.tvNombrePersonaje)
        TextView tvNombrePersonaje;

        ViewHolder(View view, Cast cast) {
            ButterKnife.bind(this, view);

            this.tvNombreActor.setText(cast.getName());
            this.tvNombrePersonaje.setText(cast.getCharacter());

        }
    }
}
