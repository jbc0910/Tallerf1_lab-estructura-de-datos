# OrdenarPila

**Asignatura:** Lab de Estructura de Datos

**Integrantes:** Javier Barrios · Jhonny Caro · Ángel Luna 

**Lenguaje:** Java

**Profesor:** Fabian Ramos

---

## Descripción

Programa en Java que ordena una pila de números enteros utilizando únicamente una pila auxiliar como estructura de apoyo. La clase `Pila` está implementada desde cero con nodos enlazados, sin usar arrays, `ArrayList`, `java.util.Stack` ni ninguna otra colección del JDK.

**Restricción cumplida:** No usar estructuras adicionales como listas o arrays.

**Pista aplicada:** Insertar elementos en orden en la pila auxiliar. Antes de cada inserción se devuelven a la original los elementos que rompan el orden, de modo que la auxiliar siempre queda ordenada tras cada paso.

---

## Estructura del código

El archivo `OrdenarPila.java` está compuesto por los siguientes elementos:

- `class Nodo` — unidad básica de la pila enlazada.
- `class Pila` — implementación propia con nodos, sin arrays.
- `ordenar()` — algoritmo de ordenamiento con pila auxiliar.
- `mostrarPila()` — visualización en consola usando solo pilas.
- `main()` — menú interactivo controlado con switch.

---

## Clase Nodo

Representa cada elemento de la pila. Guarda un valor entero y una referencia al nodo que está debajo.

**Atributos:**

- `valor` (int): número entero almacenado.
- `siguiente` (Nodo): referencia al nodo inferior; null si es el fondo.

---

## Clase Pila

Implementa el comportamiento LIFO (Last In, First Out) enlazando objetos `Nodo`. No depende de ninguna colección del JDK.

**Métodos:**

- `push(valor)` — inserta un nodo en el tope. O(1).
- `pop()` — elimina y retorna el valor del tope. O(1).
- `peek()` — consulta el tope sin eliminarlo. O(1).
- `estaVacia()` — retorna true si la pila no tiene elementos.
- `tamaño()` — retorna la cantidad de elementos en la pila.
- `vaciar()` — elimina todos los nodos.

---

## Método ordenar()

Núcleo del ejercicio. Recibe la pila original y retorna la pila auxiliar ordenada con el mayor en el tope y el menor en el fondo.

**Algoritmo:**

1. Extraer el tope de la pila original con `pop()`.
2. Mientras la auxiliar no esté vacía y su tope sea mayor que el elemento extraído, devolver ese tope a la original (pista: hacer espacio para insertar en orden).
3. Insertar el elemento en la auxiliar con `push()`. En este punto la auxiliar queda ordenada.
4. Repetir hasta vaciar la pila original.

**Complejidad:**

- Tiempo: O(n²). Cada elemento puede recorrer toda la auxiliar antes de insertarse.
- Espacio adicional: O(n). Solo la pila auxiliar, sin arrays ni listas.

---

## Método mostrarPila()

Imprime la pila con el tope arriba. Para no usar arrays emplea una pila temporal.

- **Fase 1 — Volcado:** hace `pop()` de la original imprimiendo cada valor. El primero en salir es el tope real. Los valores se apilan en `temp`.
- **Fase 2 — Restauración:** hace `pop()` de `temp` de vuelta a la original, dejándola idéntica a como estaba.

---

## Menú principal

Bucle `do-while` con `switch`. Todos los datos los ingresa el usuario en tiempo de ejecución. No hay valores predefinidos en el código.

| Opción | Acción | Validación |
|--------|--------|------------|
| 1 | Push — agregar número al tope | Rechaza entradas no numéricas |
| 2 | Pop — eliminar el tope | Avisa si la pila está vacía |
| 3 | Peek — consultar el tope | Avisa si la pila está vacía |
| 4 | Mostrar toda la pila | Indica si está vacía |
| 5 | Ordenar la pila con pila auxiliar | Maneja pila vacía y de un solo elemento |
| 6 | Vaciar la pila | Informa si ya estaba vacía |
| 0 | Salir | Cierra el Scanner y termina el programa |

---

## Verificación de restricciones

| Restricción / Pista | Cumplida | Evidencia |
|---------------------|:--------:|-----------|
| No usar arrays | ✅ | No existe ningún `int[]` ni `Object[]` en el archivo |
| No usar listas del JDK | ✅ | No se importa `ArrayList`, `LinkedList` ni similar |
| No usar `java.util.Stack` | ✅ | La clase `Pila` es una implementación propia basada en `Nodo` |
| Solo una pila auxiliar en el ordenamiento | ✅ | `ordenar()` crea exactamente un objeto `Pila` auxiliar |
| Insertar elementos en orden en la auxiliar | ✅ | El `while` interno garantiza la auxiliar siempre ordenada antes de cada `push` |
| Datos ingresados por el usuario | ✅ | `Scanner` en `main`, sin valores predefinidos en el código |
