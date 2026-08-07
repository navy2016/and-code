package com.yugahashimoto.andcode.runtime.local

import java.io.File

data class PiAsset(
    val name: String,
    val url: String,
    val sha256: String,
    val sizeBytes: Long,
)

/** Pinned official earendil-works/pi release metadata. */
object PiManifest {
    const val VERSION = "0.84.0"
    const val BINARY_NAME = "pi"
    const val MIN_FREE_BYTES = 180L * 1024L * 1024L
    private const val BASE = "https://github.com/earendil-works/pi/releases/download/v$VERSION/"

    val arm64 =
        PiAsset(
            name = "pi-linux-arm64.tar.gz",
            url = BASE + "pi-linux-arm64.tar.gz",
            sha256 = "67e331ab3e191e45a2197c1127a7b44e98fe04d93d4654d7cba769019cf1b694",
            sizeBytes = 43_357_862L,
        )

    val x64 =
        PiAsset(
            name = "pi-linux-x64.tar.gz",
            url = BASE + "pi-linux-x64.tar.gz",
            sha256 = "061e4fd191aaf5733b709aec30fce0693b7575a913e942990c0de9f31fc5c4db",
            sizeBytes = 43_320_845L,
        )

    fun assetFor(abi: String): PiAsset =
        when (abi) {
            "arm64-v8a", "aarch64" -> arm64
            "x86_64", "amd64" -> x64
            else -> error("Pi official Linux release does not support ABI $abi")
        }

    fun verifyArchive(
        file: File,
        abi: String,
    ) {
        RuntimeArchive.verifySha256(file, assetFor(abi).sha256)
    }
}
