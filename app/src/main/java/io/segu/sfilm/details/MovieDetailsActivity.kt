package io.segu.sfilm.details

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.segu.sfilm.Constants
import io.segu.sfilm.Movie
import io.segu.sfilm.R

/**
 * Created by camlh on 8/8/2017.
 */

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras != null && extras.containsKey(Constants.MOVIE)) {
                val movie = extras.getParcelable<Movie>(Constants.MOVIE)
                if (movie != null) {
                    val movieDetailsFragment = MovieDetailsFragment.getInstance(movie)
                    supportFragmentManager.beginTransaction().add(R.id.frame_container, movieDetailsFragment).commit()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }
}