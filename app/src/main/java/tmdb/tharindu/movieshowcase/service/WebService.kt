package tmdb.tharindu.movieshowcase.service

import tmdb.tharindu.movieshowcase.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WebService {

    companion object {
        const val BASE_URL: String = ("https://api.themoviedb.org/3/")
        const val API_KEY: String = "api_key"
        const val GET_MOVIES: String = "movie/now_playing"
        const val GET_SIMILAR_MOVIES: String = "movie/{movie_id}/similar"
    }

    @GET(GET_MOVIES)
     fun getMostPopular(@Query("page") page: String, @Query(API_KEY) apiKey: String): Single<MovieResponse>

    @GET(GET_SIMILAR_MOVIES)
     fun getSimilarMovies(@Path("movie_id") id: Int, @Query(API_KEY) apiKey: String): Single<MovieResponse>

}