package com.inter.interapp.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.inter.interapp.data.model.LoginResponse
import com.inter.interapp.data.model.TablaSchema

/**
 * Helper para manejo de base de datos SQLite local.
 * Gestiona las tablas de usuario y esquemas sincronizados.
 */
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "interapp.db"
        private const val DB_VERSION = 1

        const val TABLE_USUARIO = "usuario"
        const val COL_USUARIO = "usuario"
        const val COL_IDENTIFICACION = "identificacion"
        const val COL_NOMBRE = "nombre"

        const val TABLE_TABLAS = "tablas_schema"
        const val COL_NOMBRE_TABLA = "nombre_tabla"
        const val COL_DESCRIPCION = "descripcion"
        const val COL_CANTIDAD = "cantidad_registros"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """CREATE TABLE $TABLE_USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_USUARIO TEXT,
                $COL_IDENTIFICACION TEXT,
                $COL_NOMBRE TEXT
            )"""
        )
        db.execSQL(
            """CREATE TABLE $TABLE_TABLAS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOMBRE_TABLA TEXT,
                $COL_DESCRIPCION TEXT,
                $COL_CANTIDAD INTEGER
            )"""
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TABLAS")
        onCreate(db)
    }

    /** Guarda el usuario autenticado (limpia datos anteriores primero) */
    fun guardarUsuario(login: LoginResponse) {
        val db = writableDatabase
        db.delete(TABLE_USUARIO, null, null)
        val values = ContentValues().apply {
            put(COL_USUARIO, login.Usuario ?: "")
            put(COL_IDENTIFICACION, login.Identificacion ?: "")
            put(COL_NOMBRE, login.Nombre ?: "")
        }
        db.insert(TABLE_USUARIO, null, values)
        db.close()
    }

    /** Obtiene el usuario guardado localmente */
    fun obtenerUsuario(): LoginResponse? {
        val db = readableDatabase
        val cursor = db.query(TABLE_USUARIO, null, null, null, null, null, null)
        return if (cursor.moveToFirst()) {
            val usuario = LoginResponse(
                Usuario = cursor.getString(cursor.getColumnIndexOrThrow(COL_USUARIO)),
                Identificacion = cursor.getString(cursor.getColumnIndexOrThrow(COL_IDENTIFICACION)),
                Nombre = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE)),
                Apellido1 = null,
                Apellido2 = null,
                Cargo = null,
                MensajeResultado = null,
                TokenJWT = null,
                IdLocalidad = null,
                NombreLocalidad = null,
                NomRol = null,
                IdRol = null,
                Mensaje = null
            )
            cursor.close()
            db.close()
            usuario
        } else {
            cursor.close()
            db.close()
            null
        }
    }

    /** Guarda el esquema de tablas (limpia datos anteriores primero) */
    fun guardarTablas(tablas: List<TablaSchema>) {
        val db = writableDatabase
        db.delete(TABLE_TABLAS, null, null)
        tablas.forEach { tabla ->
            val values = ContentValues().apply {
                put(COL_NOMBRE_TABLA, tabla.NombreTabla ?: "")
                put(COL_DESCRIPCION, tabla.Descripcion ?: "")
                put(COL_CANTIDAD, tabla.CantidadRegistros ?: 0)
            }
            db.insert(TABLE_TABLAS, null, values)
        }
        db.close()
    }

    /** Obtiene todas las tablas guardadas localmente */
    fun obtenerTablas(): List<TablaSchema> {
        val db = readableDatabase
        val cursor = db.query(TABLE_TABLAS, null, null, null, null, null, null)
        val lista = mutableListOf<TablaSchema>()
        while (cursor.moveToNext()) {
            lista.add(
                TablaSchema(
                    NombreTabla = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE_TABLA)),
                    Descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPCION)),
                    CantidadRegistros = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CANTIDAD))
                )
            )
        }
        cursor.close()
        db.close()
        return lista
    }
}