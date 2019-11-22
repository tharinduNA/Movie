package tmdb.tharindu.movieshowcase.di.component

import tmdb.tharindu.movieshowcase.App
import tmdb.tharindu.movieshowcase.di.builder.ActivityBuilder
import tmdb.tharindu.movieshowcase.di.module.AppModule
import tmdb.tharindu.movieshowcase.di.module.NetworkModule
import tmdb.tharindu.movieshowcase.di.module.RepoModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class),
    (RepoModule::class),
    (ActivityBuilder::class)])

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}