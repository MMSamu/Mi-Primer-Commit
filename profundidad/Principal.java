package profundidad;

import java.util.ArrayList;
import java.util.Random;

public class Principal {

    public static void main(String[] args) {

        ArbolAVL arbolAVL = new ArbolAVL();

        ArbolABB arbolABB = new ArbolABB();

        ArrayList<Integer> listaOrdenada = new ArrayList<>();

        Random random = new Random();

        // Llenar la lista con 1024 valores

        for (int i = 0; i < 1024; i++) {

            listaOrdenada.add(i);

        }

        // Copia para la eliminación

        ArrayList<Integer> copia = new ArrayList<>(listaOrdenada);

        // Insertar valores aleatorios en ambos árboles

        while (!listaOrdenada.isEmpty()) {

            int indice = random.nextInt(listaOrdenada.size());

            int numero = listaOrdenada.remove(indice);

            arbolAVL.insertar(numero);

            arbolABB.insertar(numero);

        }

        // Mostrar altura antes de eliminar

        System.out.println("Altura del árbol AVL antes de eliminar: " + arbolAVL.altura());

        System.out.println("Altura del árbol ABB antes de eliminar: " + arbolABB.altura());

        // Eliminar los primeros 512 elementos

        for (int i = 0; i < 512; i++) {

            int valor = copia.get(i);

            arbolAVL.eliminar(valor);

            arbolABB.eliminar(valor);

        }

        // Mostrar altura después de eliminar

        System.out.println("Altura del árbol AVL después de eliminar 512: " + arbolAVL.altura());

        System.out.println("Altura del árbol ABB después de eliminar 512: " + arbolABB.altura());

        // Recorridos
        
        System.out.println("\nRecorrido inorden del árbol AVL:");

        arbolAVL.imprimirInorden();

        System.out.println("\nRecorrido inorden del árbol ABB:");

        arbolABB.imprimirInorden();

    }
}


