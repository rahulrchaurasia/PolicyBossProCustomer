package com.policyboss.customer.core.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class) // Scopes this player to the lifecycle of ViewModels
object MediaModule {

    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer {
        // This tells Hilt exactly how to construct the ExoPlayer
        return ExoPlayer.Builder(context).build()
    }
}