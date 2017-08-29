package cl.wower.moviestowatch.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import cl.wower.moviestowatch.MainActivity;
import cl.wower.moviestowatch.R;

public class MovieActivity extends AppCompatActivity {


    private Movie movie;
    private CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        long id = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);

        movie = Movie.findById(Movie.class, id);

        checkBox = (CheckBox) findViewById(R.id.ProveCb);

        getSupportActionBar().setTitle(movie.getName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(checkBox.isChecked());
        movie.save();
    }
}
