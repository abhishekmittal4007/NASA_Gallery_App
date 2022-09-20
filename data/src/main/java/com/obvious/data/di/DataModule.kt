package com.obvious.data.di

import com.obvious.data.datastore.NasaDataStore
import com.obvious.data.datastore.NasaDataStoreFactory
import com.obvious.data.repository.NasaDataRepository
import com.obvious.domain.repository.NasaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
  @Binds abstract fun bindsNasaDataStore(nasaDataStoreFactory: NasaDataStoreFactory): NasaDataStore

  @Binds abstract fun bindsNasaRepository(nasaDataRepository: NasaDataRepository): NasaRepository
}
