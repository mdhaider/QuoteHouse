package com.nehal.quotehouse.repository


import com.nehal.quotehouse.model.McqResponse
import com.nehal.quotehouse.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository {
    fun getPost(map:MutableMap<String, String>): Flow<McqResponse> = flow {
        val postList = RetrofitBuilder.ROUTES.getPost(map)
        emit(postList)
    }.flowOn(Dispatchers.IO)
}