package tmdb.tharindu.movieshowcase.utility

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ProgressBar


class AppUtils {

    companion object {
        fun showLoadingDialog(context: Context): ProgressBar {
            return ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        }

        fun isConnected(context: Context): Boolean {
            val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = conMgr.activeNetworkInfo
            return when (netInfo){
                null -> false
                else -> true
            }
        }
    }

}