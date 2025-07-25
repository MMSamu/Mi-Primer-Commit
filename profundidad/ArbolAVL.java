package profundidad;

public class ArbolAVL {

    private NodoAVL raiz;

    /**
     * Devuelve la altura de un nodo.
     * Si el nodo es null, se considera que su altura es 0.
     */

    public int altura() {

        return altura(raiz);

    }
    
    private int altura(NodoAVL nodo) {

        if (nodo == null) return 0;

        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        
    }

    /**
     * Devuelve el valor máximo entre dos enteros.
     */

    private int max(int a, int b) {

        return (a > b) ? a : b;

    }

    /**
     * Rotación simple hacia la izquierda (LL).
     * Se usa cuando el subárbol derecho está desbalanceado hacia la derecha.
     */
    private NodoAVL rotacion_s_izq(NodoAVL ap) {

        NodoAVL ap1 = ap.der;

        ap.der = ap1.izq;

        ap1.izq = ap;

        // Actualizamos alturas

        ap.altura = max(altura(ap.izq), altura(ap.der)) + 1;

        ap1.altura = max(altura(ap1.izq), altura(ap1.der)) + 1;

        return ap1;

    }

    /**
     * Rotación simple hacia la derecha (RR).
     * Se usa cuando el subárbol izquierdo está desbalanceado hacia la izquierda.
     */

    private NodoAVL rotacion_s_der(NodoAVL ap) {

        NodoAVL ap1 = ap.izq;

        ap.izq = ap1.der;

        ap1.der = ap;

        // Actualizamos alturas

        ap.altura = max(altura(ap.izq), altura(ap.der)) + 1;

        ap1.altura = max(altura(ap1.izq), altura(ap1.der)) + 1;

        return ap1;

    }

    /**
     * Rotación doble izquierda-derecha (LR).
     * Se aplica cuando el subárbol izquierdo está desbalanceado hacia la derecha.
     */

    private NodoAVL rotacion_doble_izq_der(NodoAVL ap) {

        ap.izq = rotacion_s_izq(ap.izq);

        return rotacion_s_der(ap);

    }

    /**
     * Rotación doble derecha-izquierda (RL).
     * Se aplica cuando el subárbol derecho está desbalanceado hacia la izquierda.
     */

    private NodoAVL rotacion_doble_der_izq(NodoAVL ap) {

        ap.der = rotacion_s_der(ap.der);

        return rotacion_s_izq(ap);

    }

    /**
     * Método para insertar un valor al árbol.
     */

    public void insertar(int valor) {

        raiz = insertar(raiz, valor); // Llama al método recursivo

    }

    /**
     * Inserta recursivamente un valor en el árbol AVL.
     * Si es necesario, realiza rotaciones para mantener el equilibrio.
     */

    private NodoAVL insertar(NodoAVL nodo, int valor) {

        if (nodo == null) return new NodoAVL(valor);

        if (valor < nodo.valor) {

            nodo.izq = insertar(nodo.izq, valor);

            // Verificar desbalance hacia la izquierda

            if (altura(nodo.izq) - altura(nodo.der) == 2) {

                nodo = (valor < nodo.izq.valor) ? rotacion_s_der(nodo) : rotacion_doble_izq_der(nodo);

            }

        } else if (valor > nodo.valor) {

            nodo.der = insertar(nodo.der, valor);

            // Verificar desbalance hacia la derecha

            if (altura(nodo.der) - altura(nodo.izq) == 2) {

                nodo = (valor > nodo.der.valor) ? rotacion_s_izq(nodo) : rotacion_doble_der_izq(nodo);
            }
        }

        // Actualizar altura del nodo actual
        nodo.altura = max(altura(nodo.izq), altura(nodo.der)) + 1;

        return nodo;

    }

    /**
     * Método para buscar un valor en el árbol.
     */

    public boolean buscar(int valor) {

        return buscar(raiz, valor);

    }

    /**
     * Busca recursivamente si un valor existe en el árbol.
     * @return true si lo encuentra, false si no.
     */

    private boolean buscar(NodoAVL nodo, int valor) {

        if (nodo == null) return false;

        if (valor == nodo.valor) return true;

        if (valor < nodo.valor) return buscar(nodo.izq, valor);

        return buscar(nodo.der, valor);

    }

    /**
     * Método para eliminar un valor del árbol AVL.
     */

    public void eliminar(int valor) {

        raiz = eliminar(raiz, valor);

    }

    /**
     * Elimina recursivamente un valor del árbol AVL.
     * Realiza balanceo si es necesario después de la eliminación.
     */

    private NodoAVL eliminar(NodoAVL nodo, int valor) {

        if (nodo == null) return null;

        if (valor < nodo.valor) {

            nodo.izq = eliminar(nodo.izq, valor);

        } else if (valor > nodo.valor) {

            nodo.der = eliminar(nodo.der, valor);

        } else {

            // Nodo con valor encontrado

            if (nodo.izq == null || nodo.der == null) {

                nodo = (nodo.izq != null) ? nodo.izq : nodo.der;

            } else {

                // Nodo con dos hijos: buscar el menor del subárbol derecho

                NodoAVL sucesor = buscarMinimo(nodo.der);

                nodo.valor = sucesor.valor;

                nodo.der = eliminar(nodo.der, sucesor.valor);

            }

        }

        if (nodo == null) return null;

        // Actualizar altura

        nodo.altura = max(altura(nodo.izq), altura(nodo.der)) + 1;

        // Rebalancear si es necesario

        int balance = altura(nodo.izq) - altura(nodo.der);

        if (balance > 1) {

            if (altura(nodo.izq.izq) >= altura(nodo.izq.der))

                return rotacion_s_der(nodo);

            else

                return rotacion_doble_izq_der(nodo);

        }

        if (balance < -1) {

            if (altura(nodo.der.der) >= altura(nodo.der.izq))

                return rotacion_s_izq(nodo);

            else

                return rotacion_doble_der_izq(nodo);

        }

        return nodo;

    }

    /**
     * Busca el nodo con el valor mínimo en un subárbol (más a la izquierda).
     * Se usa en la eliminación cuando se reemplaza un nodo con dos hijos.
     */

    private NodoAVL buscarMinimo(NodoAVL nodo) {

        while (nodo.izq != null) nodo = nodo.izq;

        return nodo;

    }

    /**
     * Imprime el recorrido inorden del árbol AVL.
     * Los valores se imprimen en orden creciente.
     */
    
    public void imprimirInorden() {

        imprimirInorden(raiz);

        System.out.println();

    }

    private void imprimirInorden(NodoAVL nodo) {

        if (nodo != null) {

            imprimirInorden(nodo.izq);

            System.out.print(nodo.valor + " ");

            imprimirInorden(nodo.der);

        }

    }
    
}

