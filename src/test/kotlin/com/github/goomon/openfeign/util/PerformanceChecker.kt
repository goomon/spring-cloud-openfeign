package com.github.goomon.openfeign.util

fun measure(cnt: Int = 1, block: () -> Unit): Long  {
    val start = System.nanoTime()

    repeat(cnt) { block.invoke() }

    val end = System.nanoTime()
    return end - start
}

fun measureMs(cnt: Int = 1, block: () -> Unit): Long  {
    val start = System.nanoTime()

    repeat(cnt) { block.invoke() }

    val end = System.nanoTime()
    return (end - start) / 1_000_000
}
