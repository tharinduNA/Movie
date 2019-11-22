package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.service.AppRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(appRepository: AppRepository) : BaseViewModel(appRepository)