package com.inter.interapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inter.interapp.data.model.LocalidadResponse
import com.inter.interapp.data.model.LoginRequest
import com.inter.interapp.data.model.LoginResponse
import com.inter.interapp.data.model.TablaSchema
import com.inter.interapp.data.repository.AppRepository
import kotlinx.coroutines.launch

/**
 * ViewModel compartido entre pantallas.
 * Maneja el estado de la UI y la lógica de negocio.
 */
class MainViewModel : ViewModel() {

    private val repository = AppRepository()

    private val _versionMensaje = MutableLiveData<String>()
    val versionMensaje: LiveData<String> = _versionMensaje

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    private val _tablas = MutableLiveData<List<TablaSchema>>()
    val tablas: LiveData<List<TablaSchema>> = _tablas

    private val _localidades = MutableLiveData<Result<List<LocalidadResponse>>>()
    val localidades: LiveData<Result<List<LocalidadResponse>>> = _localidades

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    /**
     * Compara versión local vs remota.
     * La API retorna un String plano con el número de versión (ej: "100").
     */
    fun verificarVersion(versionLocal: String) {
        viewModelScope.launch {
            try {
                val response = repository.consultarVersion()
                if (response.isSuccessful) {
                    val versionRemota = response.body()?.trim()?.replace("\"", "") ?: "0"
                    val comparacion = compararVersiones(versionLocal, versionRemota)
                    _versionMensaje.value = when {
                        comparacion < 0 -> "⚠️ Versión local ($versionLocal) inferior a la requerida ($versionRemota). Por favor actualiza."
                        comparacion > 0 -> "ℹ️ Versión local ($versionLocal) superior a la del servidor ($versionRemota)."
                        else -> "✅ Versión actualizada ($versionLocal)."
                    }
                } else {
                    _versionMensaje.value = "No se pudo verificar la versión (código ${response.code()})"
                }
            } catch (e: Exception) {
                _versionMensaje.value = "No se pudo verificar la versión."
            }
        }
    }

    /**
     * Autentica usuario contra la API.
     *
     * NOTA TÉCNICA DOCUMENTADA:
     * El servidor de pruebas retorna Identificacion, Nombre y TokenJWT como null.
     * Esto es una limitación del ambiente de pruebas y no un error de implementación.
     * En producción estos campos deberían retornar valores válidos.
     */
    fun login() {
        viewModelScope.launch {
            try {
                val headers = mapOf(
                    "Usuario" to "pam.meredy21",
                    "Identificacion" to "987204545",
                    "Accept" to "text/json",
                    "IdUsuario" to "pam.meredy21",
                    "IdCentroServicio" to "1295",
                    "NombreCentroServicio" to "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45",
                    "IdAplicativoOrigen" to "9",
                    "Content-Type" to "application/json"
                )
                val response = repository.login(headers, LoginRequest())
                if (response.isSuccessful && response.body() != null) {
                    _loginResult.value = Result.success(response.body()!!)
                } else {
                    _loginResult.value = Result.failure(
                        Exception("Error de autenticación. Código HTTP: ${response.code()} - ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(Exception("Error de red: ${e.message}"))
            }
        }
    }

    /**
     * Carga el esquema de tablas desde la API.
     *
     * NOTA TÉCNICA DOCUMENTADA:
     * El endpoint ObtenerEsquema requiere TokenJWT para autorización.
     * El servidor de pruebas retorna HTTP 401 porque el login
     * no provee un token válido en este ambiente.
     */
    fun cargarEsquema() {
        viewModelScope.launch {
            try {
                val response = repository.obtenerEsquema()
                if (response.isSuccessful) {
                    _tablas.value = response.body() ?: emptyList()
                } else {
                    _tablas.value = emptyList()
                    _error.value = "Error al cargar tablas: HTTP ${response.code()}"
                }
            } catch (e: Exception) {
                _tablas.value = emptyList()
                _error.value = "Error de red: ${e.message}"
            }
        }
    }

    /** Carga localidades desde la API */
    fun cargarLocalidades() {
        viewModelScope.launch {
            try {
                val response = repository.obtenerLocalidades()
                if (response.isSuccessful && response.body() != null) {
                    _localidades.value = Result.success(response.body()!!)
                } else {
                    _localidades.value = Result.failure(
                        Exception("Error al cargar localidades: HTTP ${response.code()}")
                    )
                }
            } catch (e: Exception) {
                _localidades.value = Result.failure(Exception("Error de red: ${e.message}"))
            }
        }
    }

    /** Compara dos versiones. Retorna negativo, 0 o positivo */
    private fun compararVersiones(local: String, remota: String): Int {
        val partsLocal = local.split(".").map { it.toIntOrNull() ?: 0 }
        val partsRemota = remota.split(".").map { it.toIntOrNull() ?: 0 }
        val maxLen = maxOf(partsLocal.size, partsRemota.size)
        for (i in 0 until maxLen) {
            val l = partsLocal.getOrElse(i) { 0 }
            val r = partsRemota.getOrElse(i) { 0 }
            if (l != r) return l - r
        }
        return 0
    }
}