# InterApp — Prueba Técnica Android

Aplicación Android desarrollada en **Kotlin** para la prueba técnica de InterRapídisimo.
Consume APIs REST del ambiente de pruebas, gestiona autenticación, almacena datos localmente
y presenta información en múltiples pantallas.

---

## Tabla de Contenidos

- [Descripción General](#descripción-general)
- [Arquitectura](#arquitectura)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías y Dependencias](#tecnologías-y-dependencias)
- [Funcionalidades](#funcionalidades)
- [Flujo de la Aplicación](#flujo-de-la-aplicación)
- [Endpoints Consumidos](#endpoints-consumidos)
- [Base de Datos Local](#base-de-datos-local)
- [Notas Técnicas Documentadas](#notas-técnicas-documentadas)
- [Configuración y Ejecución](#configuración-y-ejecución)
- [Principios SOLID Aplicados](#principios-solid-aplicados)

---

## Descripción General

InterApp es una aplicación Android nativa que implementa tres capas funcionales:

- **Capa de Seguridad:** Verificación de versión y autenticación de usuario.
- **Capa de Datos:** Sincronización y almacenamiento local de esquema de tablas mediante SQLite.
- **Capa de Presentación:** Tres pantallas (Login, Home, Tablas, Localidades) con navegación fluida.

---

## Arquitectura

El proyecto sigue el patrón **MVVM (Model - View - ViewModel)**, estándar recomendado por Google para aplicaciones Android modernas.

```
┌─────────────────────────────────────────┐
│              UI (View)                  │
│  LoginFragment, HomeFragment,           │
│  TablasFragment, LocalidadesFragment    │
│  Solo muestra datos y captura eventos   │
└──────────────┬──────────────────────────┘
               │ observa LiveData / llama funciones
┌──────────────▼──────────────────────────┐
│            ViewModel                    │
│           MainViewModel                 │
│  Lógica de negocio y manejo de estados  │
└──────────────┬──────────────────────────┘
               │ solicita datos
┌──────────────▼──────────────────────────┐
│           Repository                    │
│          AppRepository                  │
│  Abstrae el acceso a fuentes de datos   │
└──────┬───────────────────┬──────────────┘
       │                   │
┌──────▼──────┐    ┌───────▼──────────────┐
│  API REST   │    │    SQLite Local       │
│  Retrofit   │    │   DatabaseHelper      │
│ (internet)  │    │  (almacenamiento)     │
└─────────────┘    └──────────────────────┘
```

---

## Estructura del Proyecto

```
app/src/main/java/com/inter/interapp/
│
├── data/
│   ├── model/
│   │   ├── LoginRequest.kt         # Cuerpo del POST de autenticación
│   │   ├── LoginResponse.kt        # Respuesta del login
│   │   ├── VersionResponse.kt      # Respuesta de versión
│   │   ├── TablaSchema.kt          # Modelo de tabla del esquema
│   │   └── LocalidadResponse.kt    # Modelo de localidad/ciudad
│   │
│   ├── network/
│   │   ├── ApiService.kt           # Definición de endpoints (interfaz Retrofit)
│   │   └── RetrofitClient.kt       # Configuración del cliente HTTP
│   │
│   ├── db/
│   │   └── DatabaseHelper.kt       # Gestión de base de datos SQLite local
│   │
│   └── repository/
│       └── AppRepository.kt        # Intermediario entre ViewModel y fuentes de datos
│
├── ui/
│   ├── MainViewModel.kt            # ViewModel compartido entre todas las pantallas
│   │
│   ├── login/
│   │   └── LoginFragment.kt        # Pantalla de login y verificación de versión
│   │
│   ├── home/
│   │   └── HomeFragment.kt         # Pantalla principal con datos del usuario
│   │
│   ├── tablas/
│   │   ├── TablasFragment.kt       # Pantalla de tablas sincronizadas
│   │   └── TablasAdapter.kt        # Adapter para RecyclerView de tablas
│   │
│   └── localidades/
│       ├── LocalidadesFragment.kt  # Pantalla de localidades
│       └── LocalidadesAdapter.kt   # Adapter para RecyclerView de localidades
│
└── MainActivity.kt                 # Actividad principal, contenedor de navegación

app/src/main/res/
├── layout/
│   ├── activity_main.xml           # Contenedor de navegación (FragmentContainerView)
│   ├── fragment_login.xml          # Layout pantalla Login
│   ├── fragment_home.xml           # Layout pantalla Home
│   ├── fragment_tablas.xml         # Layout pantalla Tablas
│   ├── fragment_localidades.xml    # Layout pantalla Localidades
│   ├── item_tabla.xml              # Item individual de lista de tablas
│   └── item_localidad.xml          # Item individual de lista de localidades
│
└── navigation/
    └── nav_graph.xml               # Grafo de navegación entre pantallas
```

---

## Tecnologías y Dependencias

| Tecnología | Versión | Uso |
|---|---|---|
| Kotlin | 2.0.21 | Lenguaje principal |
| Android Gradle Plugin | 8.10.1 | Build system |
| AndroidX Core KTX | 1.16.0 | Extensiones Kotlin para Android |
| AppCompat | 1.7.0 | Compatibilidad hacia atrás |
| Material Design | 1.12.0 | Componentes visuales |
| ConstraintLayout | 2.2.1 | Layouts complejos |
| Retrofit | 2.11.0 | Cliente HTTP para APIs REST |
| Retrofit Gson Converter | 2.11.0 | Conversión JSON ↔ objetos Kotlin |
| Retrofit Scalars Converter | 2.11.0 | Respuestas de texto plano (String) |
| OkHttp Logging Interceptor | 4.12.0 | Logging de peticiones HTTP |
| Gson | 2.11.0 | Serialización/deserialización JSON |
| Lifecycle ViewModel KTX | 2.9.0 | ViewModel con coroutines |
| Lifecycle Runtime KTX | 2.9.0 | Ciclo de vida con coroutines |
| Kotlinx Coroutines Android | 1.9.0 | Programación asíncrona |
| Activity KTX | 1.10.1 | Extensiones para Activity |
| Navigation Fragment KTX | 2.8.9 | Navegación entre fragments |
| Navigation UI KTX | 2.8.9 | Integración de navegación con UI |
| SQLite (nativo Android) | — | Base de datos local |

### Configuración Gradle (libs.versions.toml)

El proyecto usa **Version Catalogs** de Gradle para gestión centralizada de dependencias,
facilitando actualizaciones y evitando inconsistencias de versiones entre módulos.

---

## Funcionalidades

### 1. Verificación de Versión
- Consume el endpoint de parámetros del framework al iniciar la app.
- Compara la versión local (`versionName` del `build.gradle`) con la versión remota.
- Muestra un mensaje informativo según el resultado:
  - ⚠️ Versión local **inferior** a la requerida → sugiere actualización.
  - ℹ️ Versión local **superior** a la del servidor → lo notifica.
  - ✅ Versiones **iguales** → confirma que está actualizado.

### 2. Autenticación
- Envía credenciales al endpoint de seguridad con headers y body específicos.
- Verifica el código HTTP de respuesta:
  - Código distinto de 200 → muestra alerta con el problema.
  - Código 200 → extrae `Usuario`, `Identificacion` y `Nombre`, los guarda en SQLite.

### 3. Base de Datos SQLite Local
- Crea y gestiona una base de datos local `interapp.db`.
- Tabla `usuario`: almacena datos del usuario autenticado.
- Tabla `tablas_schema`: almacena el esquema de tablas sincronizado desde la API.

### 4. Pantalla Home
- Muestra `Usuario`, `Identificacion` y `Nombre` leídos desde SQLite.
- Botones de navegación hacia Tablas y Localidades.
- Carga el esquema de tablas en segundo plano al entrar.

### 5. Pantalla Tablas
- Muestra las tablas sincronizadas desde SQLite en un `RecyclerView`.
- Si no hay datos disponibles, muestra un mensaje explicativo documentado.

### 6. Pantalla Localidades
- Consume el endpoint de localidades en tiempo real.
- Muestra `AbreviacionCiudad` y `NombreCompleto` de cada registro en un `RecyclerView`.
- Muestra un `ProgressBar` mientras carga.
- Maneja errores de red con mensajes informativos.

---

## Flujo de la Aplicación

```
Inicio
  └─→ LoginFragment
        ├─→ Verificar versión (API) → mostrar mensaje
        └─→ Click INGRESAR
              ├─→ POST login (API)
              │     ├─→ Error HTTP → mostrar alerta
              │     └─→ HTTP 200 → guardar en SQLite → navegar a Home
              │
              └─→ HomeFragment
                    ├─→ Leer usuario desde SQLite → mostrar datos
                    ├─→ Cargar esquema en segundo plano (API)
                    ├─→ Click TABLAS → TablasFragment
                    │       └─→ Leer tablas desde SQLite → mostrar lista
                    └─→ Click LOCALIDADES → LocalidadesFragment
                                └─→ GET localidades (API) → mostrar lista
```

---

## Endpoints Consumidos

### GET — Verificar Versión
```
https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/
ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl
```
- **Respuesta:** String plano con número de versión (ej: `"100"`)
- **HTTP esperado:** 200

---

### POST — Autenticación
```
https://apitesting.interrapidisimo.co/FtEntregaElectronica/MultiCanales/
ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp
```
- **Headers requeridos:**

| Header | Valor |
|---|---|
| Usuario | pam.meredy21 |
| Identificacion | 987204545 |
| Accept | text/json |
| IdUsuario | pam.meredy21 |
| IdCentroServicio | 1295 |
| NombreCentroServicio | PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45 |
| IdAplicativoOrigen | 9 |
| Content-Type | application/json |

- **Body:**
```json
{
  "Mac": "",
  "NomAplicacion": "Controller APP",
  "Password": "SW50ZXIyMDIx\n",
  "Path": "",
  "Usuario": "cGFtLm1lcmVkeTIx\n"
}
```
- **HTTP esperado:** 200

---

### GET — Esquema de Tablas
```
https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/
SincronizadorDatos/ObtenerEsquema/true
```
- **HTTP esperado:** 200 (requiere TokenJWT)

---

### GET — Localidades
```
https://apitesting.interrapidisimo.co/apicontrollerpruebas/api/
ParametrosFramework/ObtenerLocalidadesRecogidas
```
- **HTTP esperado:** 200
- **Campos utilizados:** `AbreviacionCiudad`, `NombreCompleto`

---

## Base de Datos Local

### Tabla: `usuario`

| Campo | Tipo | Descripción |
|---|---|---|
| id | INTEGER PK | Identificador autoincremental |
| usuario | TEXT | Nombre de usuario autenticado |
| identificacion | TEXT | Número de identificación |
| nombre | TEXT | Nombre completo del usuario |

### Tabla: `tablas_schema`

| Campo | Tipo | Descripción |
|---|---|---|
| id | INTEGER PK | Identificador autoincremental |
| nombre_tabla | TEXT | Nombre de la tabla del esquema |
| descripcion | TEXT | Descripción de la tabla |
| cantidad_registros | INTEGER | Número de registros que contiene |

---

## Notas Técnicas Documentadas

### Login devuelve campos nulos
El servidor de pruebas (`apitesting.interrapidisimo.co`) retorna los campos
`Identificacion`, `Nombre` y `TokenJWT` como `null` en la respuesta del login.
El campo `MensajeResultado: 0` confirma autenticación exitosa en el servidor.
Esta es una limitación del ambiente de pruebas, no un error de implementación.
En producción estos campos deberían retornar valores válidos.

### Endpoint ObtenerEsquema retorna HTTP 401
El endpoint de sincronización de esquema requiere un `TokenJWT` válido en el header
de autorización. Dado que el login del ambiente de pruebas no provee este token,
el servidor responde con `HTTP 401 - No está autorizado`.
La implementación está completa y funcional; el error es exclusivo del ambiente de pruebas.

### Versión retorna String plano
El endpoint de versión retorna un String simple (ej: `"100"`) en lugar de un objeto JSON.
Se usa `ScalarsConverterFactory` antes de `GsonConverterFactory` en Retrofit para manejar
esta respuesta correctamente.

---

## Configuración y Ejecución

### Requisitos
- Android Studio Hedgehog o superior
- JDK 17
- Android SDK API 26 o superior
- Dispositivo o emulador con Android 8.0+

### Pasos para ejecutar

1. Clona o descarga el proyecto.
2. Ábrelo en Android Studio con **File → Open**.
3. Espera que Gradle sincronice las dependencias automáticamente.
4. Si no sincroniza solo: **File → Sync Project with Gradle Files**.
5. Conecta un dispositivo Android o inicia un emulador.
6. Presiona ▶️ **Run** o usa `Shift + F10`.

### Permisos requeridos
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Principios SOLID Aplicados

**S — Single Responsibility (Responsabilidad Única)**
Cada clase tiene un único trabajo: `ApiService` solo define endpoints, `DatabaseHelper`
solo maneja SQLite, `AppRepository` solo coordina fuentes de datos.

**O — Open/Closed (Abierto/Cerrado)**
La interfaz `ApiService` permite agregar nuevos endpoints sin modificar los existentes.

**D — Dependency Inversion (Inversión de Dependencias)**
El `MainViewModel` depende de `AppRepository` (abstracción), no directamente de
`RetrofitClient` o `DatabaseHelper` (implementaciones concretas).

---

## Autor

Proyecto desarrollado como prueba técnica Android para InterRapídisimo.
Lenguaje: Kotlin | Patrón: MVVM | Min SDK: 26 | Target SDK: 35
