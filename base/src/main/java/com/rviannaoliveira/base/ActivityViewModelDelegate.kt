package com.rviannaoliveira.base

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.rviannaoliveira.di.DaggerViewModelFactory
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ActivityViewModelDelegate<T : BaseViewModel>(
    private val clazz: KClass<T>,
    private val activity: FragmentActivity,
    private val vmFactory: () -> DaggerViewModelFactory
) : ReadWriteProperty<FragmentActivity, T> {

    override fun getValue(thisRef: FragmentActivity, property: KProperty<*>): T {
        return ViewModelProviders.of(activity, vmFactory.invoke()).get(clazz.java).apply {
            thisRef.lifecycle.addObserver(this)
        }
    }

    override fun setValue(thisRef: FragmentActivity, property: KProperty<*>, value: T) {}
}
