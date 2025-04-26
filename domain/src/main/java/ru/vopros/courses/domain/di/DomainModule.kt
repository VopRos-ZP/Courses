package ru.vopros.courses.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vopros.courses.domain.usecase.ContainsCourseInFavoritesUseCase
import ru.vopros.courses.domain.usecase.DeleteFavoriteCourseUseCase
import ru.vopros.courses.domain.usecase.GetCourseListUseCase
import ru.vopros.courses.domain.usecase.GetFavoriteCourseListUseCase
import ru.vopros.courses.domain.usecase.InsertFavoriteCourseUseCase

private val useCaseModule = module {
    singleOf(::GetCourseListUseCase)
    singleOf(::GetFavoriteCourseListUseCase)
    singleOf(::ContainsCourseInFavoritesUseCase)
    singleOf(::InsertFavoriteCourseUseCase)
    singleOf(::DeleteFavoriteCourseUseCase)
}

val domainModule = module {
    includes(useCaseModule)
}