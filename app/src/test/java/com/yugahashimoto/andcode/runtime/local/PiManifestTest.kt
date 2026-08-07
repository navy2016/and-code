package com.yugahashimoto.andcode.runtime.local

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PiManifestTest {
    @Test
    fun `maps Android ABIs to official Linux release assets`() {
        assertEquals("pi-linux-arm64.tar.gz", PiManifest.assetFor("arm64-v8a").name)
        assertEquals("pi-linux-arm64.tar.gz", PiManifest.assetFor("aarch64").name)
        assertEquals("pi-linux-x64.tar.gz", PiManifest.assetFor("x86_64").name)
        assertEquals("pi-linux-x64.tar.gz", PiManifest.assetFor("amd64").name)
    }

    @Test
    fun `rejects unsupported ABI`() {
        assertThrows(IllegalStateException::class.java) { PiManifest.assetFor("armeabi-v7a") }
    }

    @Test
    fun `pins official release checksums`() {
        assertEquals(
            "67e331ab3e191e45a2197c1127a7b44e98fe04d93d4654d7cba769019cf1b694",
            PiManifest.arm64.sha256,
        )
        assertEquals(
            "061e4fd191aaf5733b709aec30fce0693b7575a913e942990c0de9f31fc5c4db",
            PiManifest.x64.sha256,
        )
    }
}
