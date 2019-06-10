package com.example.mypersonaltrainer.app

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.mypersonaltrainer.app.inject.AppComponent
import com.example.mypersonaltrainer.app.inject.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class App: Application(), HasSupportFragmentInjector {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}