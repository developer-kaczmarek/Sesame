package me.aartikov.androidarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.aartikov.androidarchitecture.list.data.MoviePagedLoader
import me.aartikov.androidarchitecture.list.data.MovieService
import me.aartikov.androidarchitecture.list.domain.Movie
import me.aartikov.androidarchitecture.profile.data.ProfileGateway
import me.aartikov.androidarchitecture.profile.domain.Profile
import me.aartikov.lib.loading.paged.PagedLoading
import me.aartikov.lib.loading.simple.Loading
import me.aartikov.lib.loading.simple.OrdinaryLoading
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DiModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideProfileLoading(profileGateway: ProfileGateway): Loading<Profile> {
        return OrdinaryLoading(profileGateway::loadProfile)
    }

    @Provides
    fun provideMovieLoader(movieService: MovieService) : PagedLoading<Movie> {
        return PagedLoading(MoviePagedLoader(movieService))
    }
}