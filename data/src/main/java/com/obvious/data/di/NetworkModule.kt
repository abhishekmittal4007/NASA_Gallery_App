package com.obvious.data.di

import android.content.Context
import com.obvious.data.api.Api
import com.obvious.remote.Host
import com.obvious.remote.network.RetrofitServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun providesApi(@ApplicationContext context: Context): Api {
    return RetrofitServiceFactory.get(Host.API_DOMAIN, context).create(Api::class.java)
  }
}
