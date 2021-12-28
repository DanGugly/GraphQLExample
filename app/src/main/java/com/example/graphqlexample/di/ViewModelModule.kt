package com.example.graphqlexample.di

import com.example.graphqlexample.repository.CharacterRepository
import com.example.graphqlexample.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
 abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: CharacterRepositoryImpl): CharacterRepository
}