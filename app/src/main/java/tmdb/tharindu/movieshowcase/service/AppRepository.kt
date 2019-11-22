package tmdb.tharindu.movieshowcase.service

import tmdb.tharindu.movieshowcase.model.MovieResponse
import tmdb.tharindu.movieshowcase.utility.AppConstants
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val webService: WebService) {

    fun getMovies(page: String): Single<MovieResponse> = webService.getMostPopular(page, AppConstants.API_KEY)

    fun getSimilarMovies(movieId: Int): Single<MovieResponse> = webService.getSimilarMovies(movieId, AppConstants.API_KEY)

}