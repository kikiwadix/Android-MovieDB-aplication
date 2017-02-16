package com.example.juanpabloss.practica2trimestremoviedb.Actividades;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.juanpabloss.practica2trimestremoviedb.Adaptadores.AdaptadorDetallesPeliculaFragment;
import com.example.juanpabloss.practica2trimestremoviedb.Adaptadores.AdaptadorPeliculas;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.Pelicula;
import com.example.juanpabloss.practica2trimestremoviedb.Modelos.PeliculasResponse;
import com.example.juanpabloss.practica2trimestremoviedb.R;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ApiClient;
import com.example.juanpabloss.practica2trimestremoviedb.Resto.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public List<Pelicula> listaPeliculas;

    public ListView listViewPelis;

    public final static String API_KEY = "9e33bfac111ee79c55763fa020435e5c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPelis = (ListView) findViewById(R.id.listViewPeliculas);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PeliculasResponse> call = apiService.getNowPlayingMovies(API_KEY);


        call.enqueue(new Callback<PeliculasResponse>() {
            @Override
            public void onResponse(Call<PeliculasResponse> call, Response<PeliculasResponse> response) {
                listaPeliculas = response.body().getResults();
                AdaptadorPeliculas adaptadorPeliculas = new AdaptadorPeliculas(MainActivity.this, listaPeliculas);
                listViewPelis.setAdapter(adaptadorPeliculas);

            }

            @Override
            public void onFailure(Call<PeliculasResponse> call, Throwable t) {

            }


        });

        listViewPelis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                AdaptadorDetallesPeliculaFragment fragment = new AdaptadorDetallesPeliculaFragment(listaPeliculas.get(position));

                //me daba problemas el bundle, y he pasado los detalles de la pelicula clickada en el constructor, justo arriba^
                /*
                Bundle data = new Bundle();

                data.putString("titulo", listaPeliculas.get(position).getTitle());
                data.putString("descripcion", listaPeliculas.get(position).getOverview());
                data.putInt("id", listaPeliculas.get(position).getId());
                data.putDouble("valoracion", listaPeliculas.get(position).getVoteAverage());
                data.putString("caratula", listaPeliculas.get(position).getPosterPath());
                fragment.setArguments(data);
                */

                transaction.replace(R.id.principal, fragment).addToBackStack(null);

                transaction.commit();





            }



        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
