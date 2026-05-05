package com.ao.finantialtracking.network.di

import com.ao.finantialtracking.network.TransactionRemoteDataSource
import com.ao.finantialtracking.network.TransactionRemoteDataSourceImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindTransactionRemoteDataSource(
        transactionRemoteDataSourceImpl: TransactionRemoteDataSourceImpl
    ): TransactionRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideFirestore(): FirebaseFirestore = Firebase.firestore

        @Provides
        @Singleton
        fun provideAuth(): FirebaseAuth = Firebase.auth

        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder()
            .build()
    }
}