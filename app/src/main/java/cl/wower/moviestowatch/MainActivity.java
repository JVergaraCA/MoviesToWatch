package cl.wower.moviestowatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockDialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.wower.moviestowatch.models.Movie;
import cl.wower.moviestowatch.models.MovieActivity;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    public static final String MOVIE_ID = "cl.wower.moviestowatch.KEY.MOVIE_ID";
    private List<Movie> movies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.MovieNameEt);
        Button button = (Button) findViewById(R.id.SaveBtn);
        Button buttonLt = (Button) findViewById(R.id.LastMovieBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString();
                if(name.trim().length() > 0){
                    Movie movie = new Movie(name, false);
                    movie.save();
                    movies = getMovies();
                    editText.setText("");
                    Toast.makeText(MainActivity.this, "Pelicula guardada", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity.this, "Debes agregar una pelicula", Toast.LENGTH_SHORT).show();
                }


            }
        });


        buttonLt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = movies.size();
                if (size > 0) {
                    Movie lastMovie = movies.get(size - 1);
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, lastMovie.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No tiene ninguna pelicula guardada", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();
    }



    private List<Movie> getMovies(){

        return Movie.find(Movie.class, "watched = 0");

    }

}
