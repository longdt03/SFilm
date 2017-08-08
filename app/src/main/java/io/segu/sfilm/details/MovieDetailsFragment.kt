package io.segu.sfilm.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import io.segu.sfilm.*
import io.segu.sfilm.base.BaseFragment
import javax.inject.Inject

/**
 * Created by camlh on 7/15/2017.
 */

class MovieDetailsFragment : BaseFragment<Int>(), MovieDetailsView, View.OnClickListener {

    @Inject
    internal var movieDetailsPresenter: MovieDetailsPresenter? = null

    @BindView(R.id.iv_movie_poster)
    lateinit var ivMoviePoster: ImageView

    @BindView(R.id.view_collapsing_toolbar)
    lateinit var viewColllapsingToolbar: CollapsingToolbarLayout

    @BindView(R.id.tv_movie_name)
    lateinit var tvMovieName: TextView

    @BindView(R.id.tv_movie_year)
    lateinit var tvMovieYear: TextView

    @BindView(R.id.tv_movie_rating)
    lateinit var tvMovieRating: TextView

    @BindView(R.id.tv_movie_description)
    lateinit var tvMovieDescription: TextView

    @BindView(R.id.tv_trailers_label)
    lateinit var tvTrailersLabel: TextView

    @BindView(R.id.view_trailers)
    lateinit var viewTrailers: LinearLayout

    @BindView(R.id.view_horiz_scroll)
    lateinit var viewHorizScroll: HorizontalScrollView

    @BindView(R.id.tv_reviews_label)
    lateinit var tvReviewsLabel: TextView

    @BindView(R.id.view_reviews)
    lateinit var viewReviews: LinearLayout

    @BindView(R.id.btn_favorite)
    lateinit var btnFavorite: FloatingActionButton

    @BindView(R.id.view_toolbar)
    lateinit var viewToolbar: Toolbar

    private var movie: Movie? = null

    companion object {
        fun getInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = args
            return movieDetailsFragment
        }
    }

    override fun onLayout(): Int = R.layout.fragment_movie_details

    override fun bindModel(inflater: LayoutInflater?, layout: View) {
        this.setToolbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        (this.activity.application as BaseApplication).createDetailsComponent().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments.get(Constants.MOVIE) as Movie
        this.movie = movie
        this.movieDetailsPresenter!!.setView(this)
        this.movieDetailsPresenter!!.showDetails(movie)
        this.movieDetailsPresenter!!.showFavoriteButton(movie)
    }

    private fun setToolbar() {
        this.viewColllapsingToolbar.setContentScrimColor(ContextCompat.getColor(this.activity, R.color.color_primary))
        this.viewColllapsingToolbar.title = getString(R.string.movie_details)
        this.viewColllapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
        this.viewColllapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
        this.viewColllapsingToolbar.isTitleEnabled = true

        this.activity.setSupportActionBar(this.viewToolbar)
        val actionBar = this.activity.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showDetails(movie: Movie) {
        Glide.with(this.activity).load(movie.backdropPath).into(this.ivMoviePoster)
        this.tvMovieName.text = movie.title
        this.tvMovieYear.text = getString(R.string.release_date).format(movie.realeaseDate)
        this.tvMovieRating.text = getString(R.string.rating).format(movie.voteAverage.toString())

        this.tvMovieDescription.text = movie.overview
        this.movieDetailsPresenter!!.showTrailers(movie)
        this.movieDetailsPresenter!!.showReviews(movie)
    }

    override fun showTrailers(trailers: List<Video>) {
        if (trailers.isEmpty()) {
            this.tvTrailersLabel.visibility = View.GONE
            this.viewTrailers.visibility = View.GONE
            this.viewHorizScroll.visibility = View.GONE
        } else {
            this.tvTrailersLabel.visibility = View.VISIBLE
            this.viewTrailers.visibility = View.VISIBLE
            this.viewHorizScroll.visibility = View.VISIBLE

            this.viewTrailers.removeAllViews()
            val inflater = this.activity.layoutInflater
            val picasso = Picasso.with(this.activity)
            for (trailer in trailers) {
                val thumbContainer = inflater.inflate(R.layout.video, this.viewTrailers, false)
                val ivThumbVideo = ButterKnife.findById<ImageView>(thumbContainer, R.id.iv_video_thumb)
                ivThumbVideo.tag = Video.getUrl(trailer)
                ivThumbVideo.requestLayout()
                ivThumbVideo.setOnClickListener(this)
                picasso.load(Video.getThumbnailUrl(trailer))
                        .resizeDimen(R.dimen.video_width, R.dimen.video_height)
                        .centerCrop()
                        .placeholder(R.color.color_primary)
                        .into(ivThumbVideo)

                this.viewTrailers.addView(thumbContainer)
            }
        }
    }

    override fun showReviews(reviews: List<Review>) {
        if (reviews.isEmpty()) {
            this.tvReviewsLabel.visibility = View.GONE
            this.viewReviews.visibility = View.GONE
        } else {
            this.tvReviewsLabel.visibility = View.VISIBLE
            this.viewReviews.visibility = View.VISIBLE

            this.viewReviews.removeAllViews()
            val inflater = activity.layoutInflater
            for (review in reviews) {
                val viewRoot = inflater.inflate(R.layout.review, this.viewReviews, false) as ViewGroup
                val reviewAuthor = ButterKnife.findById<TextView>(viewRoot, R.id.review_author)
                val reviewContent = ButterKnife.findById<TextView>(viewRoot, R.id.review_content)

                reviewAuthor.text = review.author
                reviewContent.text = review.content
                reviewContent.setOnClickListener(this)
                this.viewReviews.addView(viewRoot)
            }
        }
    }

    override fun showFavorited() {
        this.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this.activity, R.drawable.ic_favorite_white_24dp))
    }

    override fun showUnFavorited() {
        this.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this.activity, R.drawable.ic_favorite_border_white_24dp))
    }

    @OnClick(R.id.btn_favorite)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_video_thumb -> onThumbnailClick(view)

            R.id.review_content -> onReviewClick(view as TextView)

            R.id.favorites -> onFavoriteClick()

            else -> {
            }
        }
    }

    private fun onReviewClick(view: TextView) {
        if (view.maxLines == 5) {
            view.maxLines = 500
        } else {
            view.maxLines = 5
        }
    }

    private fun onThumbnailClick(view: View) {
        val videoUrl = view.tag as String
        val playViewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(playViewIntent)
    }

    private fun onFavoriteClick() {
        this.movieDetailsPresenter!!.onFavoriteClick(movie!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.movieDetailsPresenter!!.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        (this.activity.application as BaseApplication).releaseDetailsComponent()
    }
}