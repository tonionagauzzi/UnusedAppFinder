package com.vitantonio.nagauzzi.unusedappfinder.repository.mock

import com.vitantonio.nagauzzi.unusedappfinder.repository.PackageNameRepository

class MockPackageNameRepository : PackageNameRepository {
    override fun get() = "com.vitantonio.nagauzzi.mock.packagename"
}
