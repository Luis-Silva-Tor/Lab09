import graph.GraphLink;

public class Main {
    public static void main(String[] args) {
        //  Crear grafo no dirigido con GraphLink
        GraphLink<String> grafo = new GraphLink<>();

        // EJERCICIO 1 Y 2 
        
        // Insertar vértices
        grafo.insertVertex(1);
        grafo.insertVertex(2);
        grafo.insertVertex(3);
        grafo.insertVertex(4);

        // Insertar aristas con peso
        grafo.insertEdgeWeight(1, 2, 3);
        grafo.insertEdgeWeight(2, 3, 4);
        grafo.insertEdgeWeight(3, 4, 2);
        grafo.insertEdgeWeight(1, 4, 10);

        // Probar isConexo
        System.out.println("¿El grafo es conexo? " + grafo.isConexo());  // true

        // Probar Dijkstra (ruta más corta de 1 a 4)
        Stack<Integer> ruta = grafo.dijkstra(1, 4);
        System.out.print("Ruta más corta de 1 a 4 (Dijkstra): ");
        if (ruta != null) {
            while (!ruta.isEmpty()) {
                System.out.print(ruta.pop() + " ");
            }
        } else {
            System.out.print("No existe ruta.");
        }
        System.out.println();
    }
// EJERCICIO 3 , 4 
    
    // Ejemplo de ciclo: C4
    grafo.insertVertex(1);
    grafo.insertVertex(2);
    grafo.insertVertex(3);
    grafo.insertVertex(4);

    grafo.insertEdge(1, 2);
    grafo.insertEdge(2, 3);
    grafo.insertEdge(3, 4);
    grafo.insertEdge(4, 1);

    // Mostrar grados
    for (int i = 1; i <= 4; i++) {
        System.out.println("Grado de " + i + ": " + grafo.gradoNodo(i));
    }

    // Verificar tipos
    System.out.println("Es Camino " + grafo.esCamino());
    System.out.println("Es Ciclo " + grafo.esCiclo());
    System.out.println("Es Rueda " + grafo.esRueda());
    System.out.println("Es Completo " + grafo.esCompleto());
}

// EJERCICIO 5, 6 

//  Inserción de vértices y aristas
grafo.insertVertex(1);
grafo.insertVertex(2);
grafo.insertVertex(3);
grafo.insertVertex(4);

grafo.insertEdge(1, 2);
grafo.insertEdge(1, 3);
grafo.insertEdge(2, 4);
grafo.insertEdge(3, 4);

// a) Representación formal
System.out.println("Representacion");
System.out.println("V = {1, 2, 3, 4}");
System.out.println("E = {(1,2), (1,3), (2,4), (3,4)}\n");

// b) Lista de adyacencia
System.out.println("Lista de Adyacencia");
System.out.println(grafo);  // usa el toString()

// c) Matriz de adyacencia
grafo.mostrarMatrizAdyacencia();
}

// EJERCICIO 7 

//  Insertar vertices
grafo.insertVertex(1);
grafo.insertVertex(2);
grafo.insertVertex(3);
grafo.insertVertex(4);

//  Insertar aristas dirigidas
grafo.insertEdge(1, 2);
grafo.insertEdge(2, 3);
grafo.insertEdge(3, 4);


//  Mostrar grados de cada nodo
for (int i = 1; i <= 4; i++) {
    System.out.println("Nodo " + i + " Entrada: " + grafo.gradoEntrada(i) +
                       ", Salida: " + grafo.gradoSalida(i));
}

//  Verificar tipos
System.out.println("\nEs Camino Dirigido " + grafo.esCaminoDirigido());
System.out.println("Es Ciclo Dirigido " + grafo.esCicloDirigido());
System.out.println("Es Completo Dirigido " + grafo.esCompletoDirigido());
}

// EJERCICIO 8 

// Insertar vértices
grafo.insertVertex("A");
grafo.insertVertex("B");
grafo.insertVertex("C");
grafo.insertVertex("D");

// Insertar aristas
grafo.insertEdge("A", "B");
grafo.insertEdge("A", "C");
grafo.insertEdge("B", "D");
grafo.insertEdge("C", "D");

// a) Representación Formal
System.out.println("Representación Formal");
System.out.println("Vértices: {A, B, C, D}");
System.out.println("Aristas: {(A,B), (A,C), (B,D), (C,D)}");

// b) Lista de Adyacencia
System.out.println("\nLista de Adyacencia");
System.out.println(grafo);

// c) Matriz de Adyacencia
System.out.println("Matriz de Adyacencia");
grafo.mostrarMatrizAdyacencia();
}
 // EJERCICIO 9   
        
        // Insertar vértices
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");

        // Insertar aristas
        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");
        grafo.insertEdge("C", "D");

        // a) Representación Formal
        System.out.println("Representación Formal");
        System.out.println("Vértices: {A, B, C, D}");
        System.out.println("Aristas: {(A,B), (A,C), (B,D), (C,D)}");

        // b) Lista de Adyacencia
        System.out.println("\nLista de Adyacencia");
        System.out.println(grafo);

        // c) Matriz de Adyacencia
        System.out.println("Matriz de Adyacencia");
        grafo.mostrarMatrizAdyacencia();
    }
}
