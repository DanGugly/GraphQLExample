package com.example.graphqlexample.network

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class NetworkApi {

    fun getApolloClient() : ApolloClient{

        check(Looper.myLooper() == Looper.getMainLooper()){
            "Please call Apollo from the main thread"
        }

        // Okhttp to add time outs, interceptors
        val okHttpClient = OkHttpClient.Builder().build()

        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }

    companion object{
        private const val BASE_URL = "https://rickandmortyapi.com/graphql"
    }
}