package com.rasalexman.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.paging.DataSource
import com.rasalexman.core.common.extensions.loadingResult
import com.rasalexman.core.common.typealiases.ResultList
import com.rasalexman.core.data.dto.SResult
import com.rasalexman.coroutinesmanager.CoroutinesProvider
import com.rasalexman.coroutinesmanager.doWithTryCatchAsync
import com.rasalexman.data.source.factory.SearchDataSourceFactory
import com.rasalexman.models.remote.MovieModel
import com.rasalexman.providers.network.api.IMovieApi
import com.rasalexman.providers.network.handlers.asErrorResult
import com.rasalexman.providers.network.handlers.errorResultCatchBlock
import com.rasalexman.providers.network.responses.getResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.*

class MoviesRemoteDataSource(
    private val moviesApi: IMovieApi
) : IMoviesRemoteDataSource {

    override suspend fun getSearchDataSource(
        query: String,
        resultLiveData: MutableLiveData<SResult<Any>>
    ): DataSource.Factory<Int, MovieModel> {
        return SearchDataSourceFactory(
            moviesApi,
            query,
            resultLiveData
        )
    }

    /**
     * Get popular movies from Api
     * @param page - current selected page
     */
    override suspend fun getPopularMovies(page: Int): ResultList<MovieModel> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getPopularMovie(page).getResult { it.results } },
            catchBlock = errorResultCatchBlock()
        )

    override suspend fun getUpcomingMovies(page: Int): ResultList<MovieModel> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getUpcomingMovies(page).getResult { it.results } },
            catchBlock = errorResultCatchBlock()
        )

    override suspend fun getTopRatedMovies(pageLiveData: LiveData<Int>) = flow {
        pageLiveData.asFlow().collect { page ->
            emit(loadingResult())
            emit(moviesApi.getTopRatedMovies(page).getResult { it.results })
        }
    }.catch {
        emit(it.asErrorResult())
    }.flowOn(CoroutinesProvider.IO)

    /**
     * Get movies by genre id with pagination
     */
    override suspend fun getByGenreId(
        genreId: Int,
        lastReleaseDate: Long?
    ): SResult<List<MovieModel>> =
        requestHandler(genreId = genreId, lastReleaseDate = lastReleaseDate)

    /**
     * Get New movies list by genre id starting from first page (for swipe to refresh)
     */
    override suspend fun getNewMoviesByGenreId(genreId: Int): SResult<List<MovieModel>> {
        return requestHandler(genreId = genreId)
    }

    /**
     * Get all the movie details for current id
     */
    override suspend fun getMovieDetails(movieId: Int): SResult<MovieModel> =
        doWithTryCatchAsync(
            tryBlock = { moviesApi.getMovieDetails(movieId).getResult { it } },
            catchBlock = errorResultCatchBlock()
        )

    /**
     * Request data from API
     */
    private suspend fun requestHandler(
        genreId: Int,
        lastReleaseDate: Long? = null
    ): SResult<List<MovieModel>> = doWithTryCatchAsync(tryBlock = {
        val releaseDate = lastReleaseDate?.run { Date(lastReleaseDate) } ?: Date()
        val releaseDateFrom =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(releaseDate)
        moviesApi.getMoviesListByGenreId(genreId, 1, releaseDateFrom).getResult { it.results }
    }, catchBlock = errorResultCatchBlock())


}