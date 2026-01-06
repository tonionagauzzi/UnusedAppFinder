package com.vitantonio.nagauzzi.unusedappfinder

import org.junit.Assert.fail
import org.junit.Test

/**
 * GitHub Actions の Slack 通知を動作確認するためのテストクラス
 * 意図的に失敗させることで、check-and-test.yml の通知機能を検証する
 */
class SlackNotificationTest {
    @Test
    fun `Slack通知の動作確認のため意図的に失敗させる`() {
        fail("GitHub Actions の Slack 通知をテストするための意図的な失敗")
    }
}
