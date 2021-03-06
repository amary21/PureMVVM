package com.rasalexman.providers.network.responses

import androidx.annotation.Keep
import com.rasalexman.core.common.extensions.emptyResult
import com.rasalexman.core.common.extensions.errorResult
import com.rasalexman.core.common.extensions.successResult
import com.rasalexman.core.data.dto.SResult
import com.rasalexman.models.remote.GenreModel
import retrofit2.Response

@Keep
data class GetGenresResponse(val genres: List<GenreModel>)

data class GetMoviesListResponse<E>(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<E>
)


inline fun <reified T : Any, reified O : Any> Response<T>.getResult(alsoAction: (T) -> O): SResult<O> {
    return this.body()?.run {
        successResult(alsoAction(this))
    } ?: this.errorBody()?.let {
        errorResult(this.message(), this.code())
    } ?: emptyResult()
}
