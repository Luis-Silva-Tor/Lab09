import graph.GraphLink;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // ============================
        // 🔹 Actividad 
        // ============================
        GraphLink<Integer> grafo = new GraphLink<>();

        // Insertar vértices
        grafo.insertVertex(1);
        grafo.insertVertex(2);
        grafo.insertVertex(3);
        grafo.insertVertex(4);

        // Insertar aristas 
        grafo.insertEdge(1, 2);
        grafo.insertEdge(2, 3);
        grafo.insertEdge(3, 4);

        // Recorrido DFS desde el nodo 1
        System.out.println("DFS desde el nodo 1:");
        grafo.dfs(1); // Esperado: 1 2 3 4

        // Buscar si el vértice  existe
        System.out.println("\nExiste el vértice : " + grafo.searchVertex(3)); // true

        // Verificar si existe una arista entre 1 y 4
        System.out.println("Existe arista entre 1 y 4: " + grafo.searchEdge(1, 4)); // false

        // Eliminar arista entre 1 y 2
        grafo.removeEdge(1, 2);
        System.out.println("Sigue existiendo la arista entre 1 y 2: " + grafo.searchEdge(1, 2)); // false

        // Eliminar el vértice 
        grafo.removeVertex(2);
        System.out.println("Existe el vértice luego de eliminarlo: " + grafo.searchVertex(2)); // false

        // ============================
        // 🔹 Ejercicio 1: BFS y BFS Path
        // ============================

        // Agregar nuevos vértices y aristas
        grafo.insertVertex(5);
        grafo.insertVertex(6);
        grafo.insertEdge(1, 5);
        grafo.insertEdge(5, 6);
        grafo.insertEdge(6, 4);

        // Recorrido BFS desde el nodo 1
        System.out.print("BFS desde 1: ");
        grafo.bfs(1); // Esperado: 1 5 6 4

        // (Opcional) Mostrar la ruta más corta desde 1 hasta 4 usando BFS Path
        ArrayList<Integer> path = grafo.bfsPath(1, 4);
        System.out.println("\nRuta más corta de 1 a 4 (BFS): " + path); // Esperado: [1, 5, 6, 4]

    }
}
