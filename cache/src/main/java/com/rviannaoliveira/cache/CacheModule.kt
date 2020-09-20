package com.rviannaoliveira.cache

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun providesCache(cache: Cache): ICache
}