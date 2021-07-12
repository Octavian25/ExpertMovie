package com.octatech.expertmovie.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.octatech.expertmovie.core.ui.MovieAdapter
import com.octatech.expertmovie.core.ui.SeriesAdapter
import com.octatech.expertmovie.databinding.FragmentFavoriteBinding
import com.octatech.expertmovie.ui.detail.DetailActivityMovie
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            val seriesAdapter = SeriesAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivityMovie::class.java)
                intent.putExtra(DetailActivityMovie.EXTRA_DATA, selectedData)
                startActivity(intent)
            }



            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    movieAdapter.setData(movie)
                }
            })
            favoriteViewModel.favoriteSeries.observe(viewLifecycleOwner, { series ->
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
}