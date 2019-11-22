package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.service.AppRepository
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(appRepository: AppRepository) : ViewModel() {

    val repository = appRepository
    val compositeDisposable = CompositeDisposable()
    var isLoading = ObservableBoolean(false)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getIsLoading(): ObservableBoolean {
        return isLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

}