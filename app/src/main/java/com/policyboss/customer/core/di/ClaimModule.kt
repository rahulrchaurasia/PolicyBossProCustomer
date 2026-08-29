package com.policyboss.customer.core.di



import com.policyboss.customer.feature.claimSupport.repository.ClaimRepository
import com.policyboss.customer.feature.claimSupport.repository.MockClaimRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClaimModule {

    // 🚀 RIGHT NOW: Hilt will provide MockClaimRepository to your ViewModels
    @Binds
    @Singleton
    abstract fun bindClaimRepository(
        mockClaimRepository: MockClaimRepository
    ): ClaimRepository


    /*
    🚀 IN THE FUTURE: When the REST API is ready, you simply delete the
    bind block above, and uncomment this one. You don't have to change
    a single line of code in your ViewModels!

    @Binds
    @Singleton
    abstract fun bindClaimRepository(
        retrofitClaimRepository: RetrofitClaimRepository
    ): ClaimRepository
    */
}