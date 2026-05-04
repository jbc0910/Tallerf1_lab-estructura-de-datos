import java.util.Scanner;

/**
 * Ordenar una pila de numeros usando unicamente otra pila auxiliar.
 * Integrantes: Javier Barrios, Jhonny Caro, Angel Luna
 *
 * Restriccion cumplida: la clase Pila esta implementada con nodos
 * enlazados — no usa arrays, ArrayList ni ninguna otra coleccion.
 *
 * Pista aplicada: cada elemento se inserta en orden en la auxiliar,
 * manteniendola siempre ordenada tras cada insercion.
 */
public class OrdenarPila {

    
    static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    
    static class Pila {
        private Nodo tope;
        private int tamaño;

        Pila() {
            tope    = null;
            tamaño = 0;
        }

        
        void push(int valor) {
            Nodo nuevo = new Nodo(valor);
            nuevo.siguiente = tope;
            tope = nuevo;
            tamaño++;
        }

        
        int pop() {
            if (estaVacia()) throw new RuntimeException("Pila vacia");
            int valor = tope.valor;
            tope = tope.siguiente;
            tamaño--;
            return valor;
        }

        
        int peek() {
            if (estaVacia()) throw new RuntimeException("Pila vacia");
            return tope.valor;
        }

        boolean estaVacia() { return tope == null; }

        int tamaño()       { return tamaño; }

        void vaciar() {
            tope    = null;
            tamaño = 0;
        }
    }

    
    static Pila ordenar(Pila original) {
        Pila auxiliar = new Pila();

        while (!original.estaVacia()) {

            
            int elemento = original.pop();

            
            while (!auxiliar.estaVacia() && auxiliar.peek() > elemento) {
                original.push(auxiliar.pop());
            }

            
            auxiliar.push(elemento);
        }

        return auxiliar;
    }

    

    static void mostrarPila(Pila pila) {
        if (pila.estaVacia()) {
            System.out.println("  [ pila vacia ]");
            return;
        }

        Pila temp = new Pila();

        System.out.println("  +---------+");

        
        boolean esTope = true;
        while (!pila.estaVacia()) {
            int valor = pila.pop();
            String marca = esTope ? " <- tope" : "";
            System.out.printf("  |  %5d  |%s%n", valor, marca);
            esTope = false;
            temp.push(valor);
        }

        System.out.println("  +---------+  fondo");

       
        while (!temp.estaVacia()) {
            pila.push(temp.pop());
        }
    }

    

    static void separador() {
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        Pila pila   = new Pila();
        int opcion;

        System.out.println("===========================================");
        System.out.println("  Ordenar pila con pila auxiliar");
        System.out.println("  Javier Barrios . Jhonny Caro . Angel Luna");
        System.out.println("===========================================");

        do {
            System.out.println();
            System.out.println("  1. Agregar numero (push)");
            System.out.println("  2. Eliminar tope  (pop)");
            System.out.println("  3. Ver tope       (peek)");
            System.out.println("  4. Mostrar pila completa");
            System.out.println("  5. Ordenar la pila");
            System.out.println("  6. Vaciar la pila");
            System.out.println("  0. Salir");
            separador();
            System.out.print("  Elige una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.print("  Opcion invalida. Ingresa un numero: ");
                sc.next();
            }
            opcion = sc.nextInt();
            separador();

            switch (opcion) {

                case 1:
                    System.out.print("  Numero a agregar: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("  Ingresa un entero: ");
                        sc.next();
                    }
                    int numero = sc.nextInt();
                    pila.push(numero);
                    System.out.println("  OK: " + numero + " agregado al tope.");
                    mostrarPila(pila);
                    break;

                case 2:
                    if (pila.estaVacia()) {
                        System.out.println("  La pila esta vacia.");
                    } else {
                        int eliminado = pila.pop();
                        System.out.println("  Tope eliminado: " + eliminado);
                        mostrarPila(pila);
                    }
                    break;

                case 3:
                    if (pila.estaVacia()) {
                        System.out.println("  La pila esta vacia.");
                    } else {
                        System.out.println("  Tope actual: " + pila.peek());
                    }
                    break;

                case 4:
                    System.out.println("  Pila actual:");
                    mostrarPila(pila);
                    System.out.println("  Elementos: " + pila.tamaño());
                    break;

                case 5:
                    if (pila.estaVacia()) {
                        System.out.println("  La pila esta vacia.");
                    } else if (pila.tamaño() == 1) {
                        System.out.println("  Un solo elemento, ya esta ordenada.");
                        mostrarPila(pila);
                    } else {
                        System.out.println("  Antes de ordenar:");
                        mostrarPila(pila);
                        pila = ordenar(pila);
                        System.out.println("  Despues de ordenar (mayor en tope):");
                        mostrarPila(pila);
                    }
                    break;

                case 6:
                    if (pila.estaVacia()) {
                        System.out.println("  La pila ya estaba vacia.");
                    } else {
                        pila.vaciar();
                        System.out.println("  Pila vaciada.");
                    }
                    break;

                case 0:
                    System.out.println("  Hasta luego.");
                    break;

                default:
                    System.out.println("  Opcion no valida. Elige entre 0 y 6.");
            }

        } while (opcion != 0);

        sc.close();
    }
}