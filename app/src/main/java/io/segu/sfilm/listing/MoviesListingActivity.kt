package io.segu.sfilm.listing

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import butterknife.BindView
import io.segu.sfilm.Constants
import io.segu.sfilm.Movie
import io.segu.sfilm.R
import io.segu.sfilm.base.BaseActivity
import io.segu.sfilm.details.MovieDetailsActivity
import io.segu.sfilm.details.MovieDetailsFragment

/**
 * Created by camlh on 7/11/2017.
 */

class MoviesListingActivity : BaseActivity<Int>(), MoviesListingFragment.Callback {

    @BindView(R.id.view_toolbar)
    lateinit var toolbar: Toolbar

    private var twoPaneMode: Boolean = false

    companion object {
        val DETAILS_FRAGMENT = "DetailsFragment"
    }

    override fun onLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setToolbar()

        if (this.findViewById(R.id.movie_container) != null) {
            this.twoPaneMode = true

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.movie_container, MovieDetailsFragment())
                        .commit()
            }
        } else {
            this.twoPaneMode = false
        }
    }

    private fun setToolbar() {
        setSupportActionBar(this.toolbar)

        supportActionBar!!.setTitle(R.string.movie_guide)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onMovieLoaded(movie: Movie) {
        if (this.twoPaneMode) {
            this.loadMovieFragment(movie)
        } else {
            
        }
    }

    override fun onMovieClicked(movie: Movie) {
        if (this.twoPaneMode) {
            this.loadMovieFragment(movie)
        } else {
            this.startMovieActivity(movie)
        }
    }

    private fun startMovieActivity(movie: Movie) {
        val intent: Intent = Intent(this, MovieDetailsActivity::class.java)
        val extras: Bundle = Bundle()
        extras.putParcelable(Constants.MOVIE, movie)
        intent.putExtras(extras)
        startActivity(intent)
    }

    private fun loadMovieFragment(movie: Movie) {
        val movieDetailsFragment = MovieDetailsFragment.getInstance(movie)
        supportFragmentManager.beginTransaction().replace(R.id.movie_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit()
    }
}