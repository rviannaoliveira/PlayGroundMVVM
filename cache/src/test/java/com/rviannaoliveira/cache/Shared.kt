package com.rviannaoliveira.cache

data class CacheModelTest(val name : String, val value : String)


object Shared{
    val cacheModelTest = CacheModelTest("cache test", "cash value")
}
