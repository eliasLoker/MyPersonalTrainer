package com.example.mypersonaltrainer.app.inject

import android.app.Application
import com.example.mypersonaltrainer.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@AppScope
@Component(modules = [FragmentBuilder::class, AppModule::class, RoomModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}