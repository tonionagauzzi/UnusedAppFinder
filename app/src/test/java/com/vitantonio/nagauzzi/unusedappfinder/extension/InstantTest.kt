package com.vitantonio.nagauzzi.unusedappfinder.extension

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.Instant

@RunWith(JUnit4::class)
class InstantTest {
    @Test
    fun test_minusMonths() {
        // Input
        val output = Instant.parse("2023-05-12T09:49:20Z")
            .minusMonths(5)

        // Check Output
        assert(output == Instant.parse("2022-12-12T09:49:20Z"))
    }

    @Test
    fun test_resetTimeToStartOfDay() {
        // Input
        val output = Instant.parse("2023-05-12T09:49:20Z")
            .resetTimeToStartOfDay()

        // Check Output
        assert(output == Instant.parse("2023-05-12T00:00:00.000Z"))
    }

    @Test
    fun test_resetTimeToEndOfDay() {
        // Input
        val output = Instant.parse("2023-05-12T09:49:20Z")
            .resetTimeToEndOfDay()

        // Check Output
        assert(output == Instant.parse("2023-05-12T23:59:59.999Z"))
    }

    @Test
    fun test_resetDateToStartDayOfMonth() {
        // Input
        val output = Instant.parse("2023-05-12T09:49:20Z")
            .resetDateToStartDayOfMonth()

        // Check Output
        assert(output == Instant.parse("2023-05-01T09:49:20Z"))
    }

    @Test
    fun test_getString() {
        // Input
        val output = Instant.parse("2023-05-12T09:49:20Z")
            .getString("yyyy/MM/dd")

        // Check Output
        assert(output == "2023/05/12")
    }
}
