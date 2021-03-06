package io.segu.sfilm.listing

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import io.segu.sfilm.Movie
import io.segu.sfilm.R

/**
 * Created by camlh on 8/8/2017.
 */

class MoviesListingAdapter(private val movies: List<Movie>, private val view: MoviesListingView) : RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>() {

    private var context: Context? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        @BindView(R.id.iv_movie_poster)
        lateinit var ivMoviePoster: ImageView;

        @BindView(R.id.title_background)
        lateinit var titleBackground: View;

        @BindView(R.id.tv_movie_name)
        lateinit var tvMoviename: TextView

        var movie: Movie? = null

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun onClick(view: View) {
            this@MoviesListingAdapter.view.onMovieClicked(this.movie!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        val rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false)

        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(holder)
        holder.movie = this.movies[position]
        holder.tvMoviename.text = holder.movie!!.title
        Glide.with(this.context).load(holder.movie!!.posterPath)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into<BitmapImageViewTarget>(object : BitmapImageViewTarget(holder.ivMoviePoster) {
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        super.onResourceReady(resource, glideAnimation)

                        Palette.from(resource).generate {
                            palette ->
                            holder.titleBackground.setBackgroundColor(palette.getVibrantColor(
                                    ContextCompat.getColor(context!!, R.color.color_black_translucent_60)
                            ))
                        }
                    }
                })
    }

    override fun getItemCount() = this.movies.size
}
