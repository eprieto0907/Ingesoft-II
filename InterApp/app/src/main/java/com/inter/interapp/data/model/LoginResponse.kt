package com.inter.interapp.data.model

/**
 * Modelo de respuesta del endpoint de autenticación.
 *
 * NOTA TÉCNICA DOCUMENTADA:
 * El servidor de pruebas retorna Identificacion, Nombre y TokenJWT como null.
 * Esto es una limitación del ambiente de pruebas (apitesting.interrapidisimo.co)
 * y no un error de implementación.
 * El campo MensajeResultado=0 indica autenticación exitosa en el servidor.
 * En producción estos campos deberían retornar valores válidos.
 */
data class LoginResponse(
    val Usuario: String?,
    val Identificacion: String?,
    val Nombre: String?,
    val Apellido1: String?,
    val Apellido2: String?,
    val Cargo: String?,
    val MensajeResultado: Int?,
    val TokenJWT: String?,
    val IdLocalidad: String?,
    val NombreLocalidad: String?,
    val NomRol: String?,
    val IdRol: String?,
    val Mensaje: String?
)