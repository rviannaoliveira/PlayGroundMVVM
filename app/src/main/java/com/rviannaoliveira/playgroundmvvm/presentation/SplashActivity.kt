package com.rviannaoliveira.playgroundmvvm.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rviannaoliveira.main.presentation.MainActivity
import com.rviannaoliveira.playgroundmvvm.R
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        compositeDisposable.add(Completable.timer(1, TimeUnit.SECONDS).subscribe {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}