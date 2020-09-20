package com.rviannaoliveira.cache

import javax.inject.Inject

interface ICache {
    fun <T : Any> get(key: String): T?
    fun put(key: String, cache: Any)
    fun clearCache(key: String)
    fun clearCache()
}

class Cache @Inject constructor() : ICache {
    private val hashMap = hashMapOf<String, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(key: String): T? =
            hashMap[key] as? T


    override fun put(key: String, cache: Any) {
        hashMap[key] = cache
    }

    override fun clearCache(key: String) {
        hashMap.remove(key)
    }

    override fun clearCache() {
        hashMap.clear()
    }
}
