package com.vitantonio.nagauzzi.unusedappfinder.repository

class MockPackageNameRepository : PackageNameRepository {
    override fun get() = "com.vitantonio.nagauzzi.mock.packagename"
}
