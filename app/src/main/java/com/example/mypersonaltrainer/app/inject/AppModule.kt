package com.example.mypersonaltrainer.app.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class AppModule {
    @Provides
    @AppScope
    fun provideContext(application: Application): Context {
        return application
    }
}