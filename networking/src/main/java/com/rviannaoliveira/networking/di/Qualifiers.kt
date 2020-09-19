package com.rviannaoliveira.networking.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainScheduler
