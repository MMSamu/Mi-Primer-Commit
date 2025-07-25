package profundidad;

public class NodoAVL {

    int valor;
    int altura;
    NodoAVL izq, der;

    //Creacion del constructor

    public NodoAVL(int valor) {

        this.valor = valor;

        this.altura = 1;

        this.izq = null;

        this.der = null;

    }

}

