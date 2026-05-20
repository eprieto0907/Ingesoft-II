package com.inter.interapp.data.model

/**
 * La API de versión devuelve un String plano, no un objeto JSON.
 * Se maneja directamente como String en el repositorio.
 */
data class VersionResponse(
    val Resultado: String?
)