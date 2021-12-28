package com.example.graphqlexample.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.graphqlexample.CharactersListQuery
import com.example.graphqlexample.network.NetworkApi
import javax.inject.Inject

interface CharacterRepository {

    suspend fun queryCharactersList() : Response<CharactersListQuery.Data>
}

class CharacterRepositoryImpl @Inject constructor(
    private val webService: NetworkApi
) : CharacterRepository {

    override suspend fun queryCharactersList(): Response<CharactersListQuery.Data> =
        webService.getApolloClient().query(CharactersListQuery()).await()

}
