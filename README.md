
---


# 🚍 Sistema de Transporte - Proyecto de Programación Orientada a Objetos (POO)

## 📖 Descripción general
Este proyecto fue desarrollado como práctica del tema **Programación Orientada a Objetos (POO)** en Java. El objetivo principal es aplicar los conceptos de **herencia**, **polimorfismo**, **encapsulamiento** y **sobrecarga de métodos (overloading)** de una forma práctica y entendible.  
El sistema simula una empresa de transporte que gestiona **viajes**, **rutas**, **conductores** y **vehículos** de distintos tipos (ómnibus, avión y barco). Cada transporte calcula su costo y duración de forma diferente usando el polimorfismo.

---

## 🧩 Estructura del proyecto
El proyecto está organizado dentro del paquete `sistemaDeTransporte` y contiene las siguientes clases:

```

sistemaDeTransporte/
│
├── Main.java
├── SistemaTransporte.java
│
├── Transporte.java
├── Omnibus.java
├── Avion.java
├── Barco.java
│
├── Ruta.java
├── Conductor.java
└── Viaje.java

```

---

## ⚙️ Funcionamiento del programa
Al ejecutar el programa desde `Main.java`, se inicia un menú principal que permite interactuar con el sistema.  

**Menú principal:**
```

--- SISTEMA DE TRANSPORTE ---
1 - Ver rutas
2 - Crear viaje
3 - Ver viajes
4 - Reservar pasajeros
5 - Salir
Opción:

```

### 🧠 Explicación de cada opción:
| Opción | Descripción |
|--------|--------------|
| **1 - Ver rutas** | Muestra todas las rutas registradas en el sistema. |
| **2 - Crear viaje** | Permite crear un nuevo viaje seleccionando una ruta, asignando un transporte del mismo tipo y un conductor disponible. También pide una fecha válida. |
| **3 - Ver viajes** | Muestra todos los viajes creados, con los datos ordenados en varias líneas para mejor lectura (fecha, transporte, conductor, costo, duración, etc.). |
| **4 - Reservar pasajeros** | Permite modificar la cantidad de pasajeros de un viaje existente y recalcula el costo y el precio por pasajero. |
| **5 - Salir** | Cierra el sistema. |

---

## 🧮 Conceptos de POO aplicados

### 🔹 Herencia
La clase `Transporte` es **abstracta** y actúa como clase base para los tipos de transporte:
- `Omnibus`
- `Avion`
- `Barco`

Cada uno hereda atributos y métodos, pero sobrescribe los métodos abstractos:
- `calcularCosto(double distanciaKm)`
- `calcularDuracionHoras(double distanciaKm)`

### 🔹 Polimorfismo
Gracias al polimorfismo, el programa puede tratar a todos los transportes como un mismo tipo (`Transporte`), pero cada uno ejecuta su propia versión de los métodos según corresponda.  
Por ejemplo, un avión calcula su costo con un factor diferente que un ómnibus o un barco, sin que el sistema principal tenga que saber cuál es cuál.

### 🔹 Encapsulamiento
Cada clase tiene sus atributos **privados** y métodos **públicos** para acceder a ellos, evitando modificar los datos directamente.

### 🔹 Sobrecarga de métodos (Overloading)
En la clase `Viaje`, el método `calcularCostoTotal()` está sobrecargado:
- Uno calcula el costo sin impuestos.
- Otro recibe un parámetro booleano y, si es `true`, agrega un 10% de impuesto al total.

---

## 🧍‍♂️ Roles en el proyecto
- **Líder del Proyecto: Alvaro Giachetto** coordina reuniones y organización.
- **Desarrollador Principal:Alvaro Giachetto** implementa el código del sistema.
- **Investigador: Ricardo Viera** recopila información sobre polimorfismo y ejemplos.
- **Presentador: Leandro Barros** explica el proyecto y realiza la demostración.

---

## 🖥️ Ejemplo de salida en consola
Al crear y ver un viaje, el sistema muestra la información de manera ordenada para facilitar la lectura:

```

--- VIAJE ---
ID: 1
Fecha: 29/10/2025
Origen: Montevideo
Destino: Punta del Este
Transporte: Omnibus De Larga Distancia
Conductor: Juan
Duración estimada: 1h 41m
Pasajeros: 15
Costo operativo: $637.88
Precio por pasajero: $42.53
---------------------------

````

---

## 🧰 Cómo ejecutar el proyecto

1. **Descargar o clonar el repositorio:**

   git clone https://github.com/AlvaroGiachetto/sistemaDeTransporte.git


2. **Abrir el proyecto en tu entorno Java (Eclipse, NetBeans o VS Code).**
3. Asegúrate de que todos los archivos estén dentro del paquete `sistemaDeTransporte`.
4. Ejecuta la clase `Main.java`.
5. Interactúa con el sistema usando el menú.

---

## 📦 Tecnologías utilizadas

* Lenguaje: **Java**
* Paradigma: **Programación Orientada a Objetos (POO)**
* Librerías estándar de Java (`java.util`, `java.time`)
* Entrada por consola con `Scanner`

---

## 🧠 Objetivo académico

Este proyecto fue realizado como trabajo práctico grupal para demostrar comprensión de los conceptos de POO, especialmente **polimorfismo**.
La idea es mostrar cómo distintas clases pueden compartir una misma interfaz pero comportarse de manera diferente según su tipo.

---
Creditos o propietario: @AlvaroGiachetto

