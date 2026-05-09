# Patrones de Diseño en Java

Este proyecto contiene ejemplos prácticos de distintos patrones de diseño desarrollados en Java utilizando Apache NetBeans.

---

# Instrucciones de ejecución

Para ejecutar cada ejercicio en Apache NetBeans:

1. Abrir el proyecto correspondiente.
2. Ir al archivo `Main.java` o al archivo principal del ejercicio.
3. Click derecho sobre el archivo.
4. Seleccionar:

```text
Run File
```

o usar el atajo:

```text
Shift + F6
```

---

# Punto 1 — Singleton

## Descripción

Se implementó un sistema de login utilizando el patrón Singleton para garantizar que exista una única sesión activa en el sistema.

---

## UML

```text
┌────────────────────┐
│      Sesion        │
├────────────────────┤
│ - instancia        │
│ - usuario          │
├────────────────────┤
│ - Sesion()         │
│ + getInstancia()   │
│ + login()          │
│ + mostrarSesion()  │
└────────────────────┘
```

---

# Punto 2 — Factory Method

## Descripción

Se implementó una fábrica de vehículos que permite crear distintos tipos de objetos sin utilizar directamente el operador `new`.

---

## UML

```text
        Vehiculo
           ▲
     ┌─────┴─────┐
     │           │
   Carro       Moto

           ▲
           │
   VehiculoFactory
```

---

# Punto 3 — Builder + Facade

## Descripción

Se implementó un sistema de construcción de computadoras utilizando Builder para construir el objeto paso a paso y Facade para simplificar el acceso al sistema.

---

## UML

```text
ComputadoraBuilder
        ▲
        │
    PCBuilder ─────► Computadora

TiendaFacade ─────► PCBuilder
```

---

# Punto 4 — Factory + Adapter + Strategy

## Descripción

Se implementó un sistema de pagos que combina:

- Factory para crear métodos de pago.
- Adapter para integrar una API externa de criptomonedas.
- Strategy para cambiar dinámicamente la estrategia de pago.

---

## UML

```text
            MetodoPago
                 ▲
     ┌───────────┼───────────┐
     │           │           │
PagoTarjeta  PagoPayPal  CryptoAdapter
                                 │
                                 ▼
                             CryptoAPI


PagoFactory ─────► MetodoPago

ContextoPago ───► MetodoPago
```

---

# Patrones utilizados

| Punto | Patrones |
|---|---|
| 1 | Singleton |
| 2 | Factory Method |
| 3 | Builder + Facade |
| 4 | Factory + Adapter + Strategy |

---

# Tecnologías utilizadas

- Java
- Apache NetBeans
- Programación Orientada a Objetos
- Patrones de Diseño