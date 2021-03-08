package com.khrisna.core.utils

import com.khrisna.core.data.source.local.entity.*
import com.khrisna.core.data.source.remote.response.*
import com.khrisna.core.domain.model.*

object DataMapper {

    // Movie
    fun mapMovieEntityToDomain(input: MovieEntity?): Movie =
        input.let { movie ->
            Movie(
                id = movie?.id,
                title = movie?.title,
                overview = movie?.overview,
                poster = movie?.poster,
                backdrop = movie?.backdrop,
                releaseDate = movie?.releaseDate,
                rating = movie?.rating,
                genres = movie?.genres?.map { genre ->
                    Genre(
                        id = genre.id,
                        name = genre.name
                    )
                }
            )
        }

    fun mapMovieResponseToEntity(input: MovieResponse?): MovieEntity? =
        input?.let { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                poster = movie.poster,
                backdrop = movie.backdrop,
                releaseDate = movie.releaseDate,
                rating = movie.rating,
                genres = movie.genres?.map { genre ->
                    GenreEntity(
                        id = genre.id,
                        name = genre.name
                    )
                }
            )
        }

    // Movies
    fun mapMoviesEntityToDomain(input: MoviesEntity?): Movies =
        Movies(
            id = input?.id,
            header = input?.header,
            movies = input?.movies?.map { movie ->
                Movie(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    poster = movie.poster,
                    backdrop = movie.backdrop,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating,
                    genres = movie.genres?.map { genre ->
                        Genre(
                            id = genre.id,
                            name = genre.name
                        )
                    }
                )
            }
        )

    fun mapMoviesResponseToEntity(input: MoviesResponse, id: Int): MoviesEntity =
        MoviesEntity(
            id = id,
            header = input.header,
            movies = input.movies.map { movie ->
                MovieEntity(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    poster = movie.poster,
                    backdrop = movie.backdrop,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating,
                    genres = movie.genres?.map {
                        GenreEntity(
                            id = it.id,
                            name = it.name
                        )
                    }
                )
            }
        )

    // TVShow
    fun mapTVShowEntityToDomain(input: TVShowEntity?): TVShow =
        input.let { tvshow ->
            TVShow(
                id = tvshow?.id,
                title = tvshow?.title,
                overview = tvshow?.overview,
                poster = tvshow?.poster,
                backdrop = tvshow?.backdrop,
                firstAir = tvshow?.firstAir,
                rating = tvshow?.rating,
                genres = tvshow?.genres?.map { genre ->
                    Genre(
                        id = genre.id,
                        name = genre.name
                    )
                }
            )
        }

    fun mapTVShowResponseToEntity(input: TVShowResponse?): TVShowEntity? =
        input?.let { tvshow ->
            TVShowEntity(
                id = tvshow.id,
                title = tvshow.title,
                overview = tvshow.overview,
                poster = tvshow.poster,
                backdrop = tvshow.backdrop,
                firstAir = tvshow.firstAir,
                rating = tvshow.rating,
                genres = tvshow.genres?.map { genre ->
                    GenreEntity(
                        id = genre.id,
                        name = genre.name
                    )
                }
            )
        }

    // TVShows
    fun mapTVShowsEntityToDomain(input: TVShowsEntity?): TVShows =
        input.let { tvshows ->
            TVShows(
                id = tvshows?.id,
                header = tvshows?.header,
                tvShows = tvshows?.tvShows?.map { tvShow ->
                    TVShow(
                        id = tvShow.id,
                        title = tvShow.title,
                        overview = tvShow.overview,
                        poster = tvShow.poster,
                        backdrop = tvShow.backdrop,
                        firstAir = tvShow.firstAir,
                        rating = tvShow.rating,
                        genres = tvShow.genres?.map { genre ->
                            Genre(
                                id = genre.id,
                                name = genre.name
                            )
                        }
                    )
                }
            )
        }

    fun mapTVShowsResponseToEntity(input: TVShowsResponse?, id: Int): TVShowsEntity? =
        input?.let { tvshows ->
            TVShowsEntity(
                id = id,
                header = tvshows.header,
                tvShows = tvshows.tvShows.map { tvShow ->
                    TVShowEntity(
                        id = tvShow.id,
                        title = tvShow.title,
                        overview = tvShow.overview,
                        poster = tvShow.poster,
                        backdrop = tvShow.backdrop,
                        firstAir = tvShow.firstAir,
                        rating = tvShow.rating,
                        genres = tvShow.genres?.map { genre ->
                            GenreEntity(
                                id = genre.id,
                                name = genre.name
                            )
                        }
                    )
                }
            )
        }

    // Favorite
    fun mapFavoriteEntityToDomain(input: FavoriteEntity?): Favorite =
        input.let { favorite ->
            Favorite(
                id = favorite?.id,
                poster = favorite?.poster,
                dataId = favorite?.dataId,
                isMovie = favorite?.isMovie
            )
        }

    fun mapFavoriteDomainToEntity(input: Favorite?): FavoriteEntity =
        input.let { favorite ->
            FavoriteEntity(
                id = favorite?.id,
                poster = favorite?.poster,
                dataId = favorite?.dataId,
                isMovie = favorite?.isMovie
            )
        }

    // Search
    fun mapSearchesResponseToDomain(input: SearchesResponse?): Searches =
        input?.searches?.map {
            Search(
                id = it.id,
                poster = it.poster
            )
        }.let {
            Searches(
                searches = it
            )
        }
}