package com.gorden.happycoding.kt


fun foo(int: Int): () -> Unit = {
    println(int)
}

fun foo2(int: Int) {
    println(int)
}

val foo3 = { int: Int ->
    println(int)
}

val foo4 = foo(1)

fun foo5(flag: Boolean): String {
    return if (flag) "dive into kotlin" else "" // 这里的 a 是并发安全的吗？
}

var a = "test"
fun foo6(flag : Boolean): String {
    a = if(flag) "dive into kotlin"
    else ""
    return a
}


fun main() {
    foo(1)
    foo2(2)
    foo3(3)
    foo4()

    println({ x: Int -> println(x) }(1))

    // 从下面的两个线程可以看出：kt 的 if/else
    Thread {
        for (i in 0..10) {
            println("thread one :${foo5(true)}")
        }
    }.start()

    Thread {
        for (i in 0..10) {
            println("thread two :${foo5(false)}")
        }
    }.start()


    Thread {
        for (i in 0..10) {
            println("thread three :${foo6(true)}")
        }
    }.start()

    Thread {
        for (i in 0..10) {
            println("thread four :${foo6(false)}")
        }
    }.start()
}