package com.inter.interapp.data.repository

import com.inter.interapp.data.model.LoginRequest
import com.inter.interapp.data.model.LoginResponse
import com.inter.interapp.data.model.LocalidadResponse
import com.inter.interapp.data.model.TablaSchema
import com.inter.interapp.data.network.RetrofitClient
import retrofit2.Response

/**
 * Repositorio central que abstrae el acceso a datos remotos.
 * Principio de responsabilidad única (SOLID).
 */
class AppRepository {

    private val api = RetrofitClient.apiService

    suspend fun consultarVersion(): Response<String> = api.consultarVersion()

    suspend fun login(headers: Map<String, String>, body: LoginRequest): Response<LoginResponse> =
        api.login(headers, body)

    suspend fun obtenerEsquema(): Response<List<TablaSchema>> = api.obtenerEsquema()

    suspend fun obtenerLocalidades(): Response<List<LocalidadResponse>> = api.obtenerLocalidades()
}