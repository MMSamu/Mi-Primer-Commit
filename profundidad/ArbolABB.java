package profundidad;

public class ArbolABB {

    private NodoABB raiz;

    /**
     * Inserta un valor en el árbol ABB.
     */

    private NodoABB insertar(NodoABB nodo, int valor) {

        if (nodo == null) return new NodoABB(valor);

        if (valor < nodo.valor) {

            nodo.izq = insertar(nodo.izq, valor);

        } else if (valor > nodo.valor) {

            nodo.der = insertar(nodo.der, valor);

        }

        return nodo;

    }

    /**
     * Busca un valor en el árbol ABB.
     */

    private boolean buscar(NodoABB nodo, int valor) {

        if (nodo == null) return false;

        if (valor == nodo.valor) return true;

        if (valor < nodo.valor) return buscar(nodo.izq, valor);

        return buscar(nodo.der, valor);
        
    }

    /**
     * Elimina un valor del árbol ABB.
     */

    private NodoABB eliminar(NodoABB nodo, int valor) {

        if (nodo == null) return null;

        if (valor < nodo.valor) {

            nodo.izq = eliminar(nodo.izq, valor);

        } else if (valor > nodo.valor) {

            nodo.der = eliminar(nodo.der, valor);

        } else {

            if (nodo.izq == null) return nodo.der;

            if (nodo.der == null) return nodo.izq;

            NodoABB sucesor = minimoNodo(nodo.der);

            nodo.valor = sucesor.valor;

            nodo.der = eliminar(nodo.der, sucesor.valor);

        }

        return nodo;

    }

    /**
     * Encuentra el nodo con el valor mínimo en un subárbol.
     */

    private NodoABB minimoNodo(NodoABB nodo) {

        while (nodo.izq != null) nodo = nodo.izq;

        return nodo;

    }

     /**
     * Calcula la altura del árbol ABB.
     */

    private int altura(NodoABB nodo) {

        if (nodo == null) return 0;

        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));

    }

    /**
     * Imprime el recorrido inorden del árbol ABB.
     */

    private void imprimirInorden(NodoABB nodo) {

        if (nodo != null) {

            imprimirInorden(nodo.izq);

            System.out.print(nodo.valor + " ");

            imprimirInorden(nodo.der);

        }

    }

    public void insertar(int valor) {

        raiz = insertar(raiz, valor);

    }

    public boolean buscar(int valor) {

        return buscar(raiz, valor);

    }

    public void eliminar(int valor) {

        raiz = eliminar(raiz, valor);

    }

    public int altura() {

        return altura(raiz);

    }

    public void imprimirInorden() {

        imprimirInorden(raiz);

        System.out.println();

    }

}




