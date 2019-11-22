package tmdb.tharindu.movieshowcase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import tmdb.tharindu.movieshowcase.service.WebService
import tmdb.tharindu.movieshowcase.utility.AppConstants

class MovieUnitTest {

    @Test
    fun testConstants_strings_true(){

        //test api key
        assertEquals("176fffb59b1dff7a39cbf20587d3899e", AppConstants.API_KEY)

        //test base url
        assertEquals("https://api.themoviedb.org/3/", WebService.BASE_URL)

        //test movies end point
        assertEquals("movie/now_playing", WebService.GET_MOVIES)

        //test similar end point
        assertEquals("movie/{movie_id}/similar", WebService.GET_SIMILAR_MOVIES)
    }

}