package com.inter.interapp.data.network

import com.inter.interapp.data.model.LocalidadResponse
import com.inter.interapp.data.model.LoginRequest
import com.inter.interapp.data.model.LoginResponse
import com.inter.interapp.data.model.TablaSchema
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ApiService {

    /**
     * Consulta la versión actual requerida por el servidor.
     * Retorna un String plano (ej: "100"), no un objeto JSON.
     */
    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun consultarVersion(): Response<String>

    /**
     * Autentica al usuario.
     * NOTA: El servidor de pruebas retorna TokenJWT=null,
     * Identificacion=null y Nombre=null en este ambiente.
     */
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun login(
        @HeaderMap headers: Map<String, String>,
        @Body body: LoginRequest
    ): Response<LoginResponse>

    /**
     * Obtiene el esquema de tablas.
     * NOTA: Requiere TokenJWT en header. El ambiente de pruebas
     * retorna HTTP 401 porque el login no provee token válido.
     */
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun obtenerEsquema(): Response<List<TablaSchema>>

    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun obtenerLocalidades(): Response<List<LocalidadResponse>>
}