package com.gorden.buildsrc.plugins.legalBitmap

data class LegalBitmapConfig(private val monitorImageViewClass: String = "com.gorden.happycoding.asm.MonitorImageView") {

    val formatMonitorImageViewClass: String
        get() = monitorImageViewClass.replace(".", "/")

}