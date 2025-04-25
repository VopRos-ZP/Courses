package ru.vopros.courses.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vopros.courses.domain.usecase.GetCourseListUseCase

private val useCaseModule = module {
    singleOf(::GetCourseListUseCase)
}

val domainModule = module {
    includes(useCaseModule)
}