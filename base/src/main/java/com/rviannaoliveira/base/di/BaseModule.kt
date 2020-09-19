package com.rviannaoliveira.base.di

import com.rviannaoliveira.base.Reporter
import com.rviannaoliveira.base.ReporterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BaseModule {
    @Binds
    abstract fun bindReporter(reporter: ReporterImpl): Reporter
}