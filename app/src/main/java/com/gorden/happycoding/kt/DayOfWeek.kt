package com.gorden.happycoding.kt

enum class DayOfWeek(private val day: Int) {
    mon(1),
    tue(2)
    ;// 如果有额外的方法或者定义属性，需要加上分号

    val variable :String
    get() {
        return "kll"
    }

    fun getInt(): Int{
        return day
    }
}