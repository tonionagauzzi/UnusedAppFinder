package com.vitantonio.nagauzzi.unusedappfinder.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppUsageRepository(
        appUsageRepositoryImpl: AppUsageRepositoryImpl,
    ): AppUsageRepository

    @Binds
    @Singleton
    abstract fun bindPackageNameRepository(
        packageNameRepositoryImpl: PackageNameRepositoryImpl,
    ): PackageNameRepository
}
