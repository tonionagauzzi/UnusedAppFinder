package com.vitantonio.nagauzzi.unusedappfinder.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface PackageNameRepository {
    fun get(): String
}

class PackageNameRepositoryImpl
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : PackageNameRepository {
        override fun get(): String = context.packageName
    }
