package com.octatech.expertmovie.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.octatech.expertmovie.core.source.Resource
import com.octatech.expertmovie.core.ui.MovieAdapter
import com.octatech.expertmovie.core.ui.SeriesAdapter
import com.octatech.expertmovie.favorite.databinding.ActivityFavoriteBinding
import com.octatech.expertmovie.ui.detail.DetailActivityMovie
import com.octatech.expertmovie.ui.detail.DetailActivitySeries
import com.octatech.expertmovie.ui.home.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel : FavoriteViewModel by viewModel()
    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(FavoriteModule)

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        getMoviefavorite()
    }

    private fun getMoviefavorite() {
        val movieAdapter = MovieAdapter()
        val seriesAdapter = SeriesAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivityMovie::class.java)
            intent.putExtra(DetailActivityMovie.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        seriesAdapter.onItemClick = { selectedData ->
                val intentSeries = Intent(this, DetailActivitySeries::class.java)
                intentSeries.putExtra(DetailActivitySeries.EXTRA_DATA, selectedData)
                startActivity(intentSeries)
        }


        favoriteViewModel.FavoriteMovie.observe(this, { movie ->
            if (movie != null) {
                movieAdapter.setData(movie)
            }
        })
        favoriteViewModel.FavoriteSeries.observe(this, { series ->
            if (series != null) {
                seriesAdapter.setData(series)
            }
        })


        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false )
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        with(binding.rvSeries) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = seriesAdapter
        }
    }
}