package com.rasalexman.data.repository

import com.rasalexman.core.data.base.IBaseRepository
import com.rasalexman.core.data.dto.SResult
import com.rasalexman.models.local.GenreEntity
import com.rasalexman.data.source.local.IGenresLocalDataSource
import com.rasalexman.data.source.remote.IGenresRemoteDataSource

interface IGenresRepository : IBaseRepository<IGenresLocalDataSource, IGenresRemoteDataSource> {

    suspend fun getLocalGenresList(): SResult<List<GenreEntity>>
    suspend fun getRemoteGenresList(): SResult<List<GenreEntity>>

    suspend fun saveGenresList(genresList: List<GenreEntity>)
}