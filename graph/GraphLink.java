package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;


import listlinked.ListLinked;

public class GraphLink<E> {
    // Lista de vértices del grafo
    protected ListLinked<Vertex<E>> listVertex;

    // Constructor: inicializa el grafo vacío
    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    // Inserta un vértice al grafo si no existe previamente
    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.insertLast(new Vertex<E>(data));
        }
    }

    // Busca si un vértice existe en el grafo
    public boolean searchVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    // Retorna el objeto vértice si existe en el grafo
    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    // Inserta una arista no dirigida entre dos vértices
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) {
            vOri.listAdj.insertLast(new Edge<E>(vDes)); // Solo de origen a destino
        }
    }


    // Verifica si existe una arista entre dos vértices
    public boolean searchEdge(E v, E z) {
        Vertex<E> v1 = getVertex(v);
        if (v1 != null) {
            for (Edge<E> e : v1.listAdj) {
                if (e.getRefDest().getData().equals(z)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Elimina la arista entre dos vértices
    public void removeEdge(E v, E z) {
        Vertex<E> v1 = getVertex(v);
        Vertex<E> v2 = getVertex(z);
        if (v1 != null && v2 != null) {
            v1.listAdj.remove(new Edge<E>(v2));
            v2.listAdj.remove(new Edge<E>(v1));
        }
    }

    // Elimina un vértice y todas las aristas que lo conectan
    public void removeVertex(E v) {
        Vertex<E> toRemove = getVertex(v);
        if (toRemove != null) {
            for (Vertex<E> vertex : listVertex) {
                vertex.listAdj.remove(new Edge<E>(toRemove));
            }
            listVertex.remove(toRemove);
        }
    }

    // Recorrido en profundidad (DFS) desde un vértice
    public void dfs(E v) {
        Vertex<E> start = getVertex(v);
        if (start == null) return;

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        dfsRecursive(start, visited);
    }

    // Función recursiva auxiliar para DFS
    private void dfsRecursive(Vertex<E> v, ListLinked<Vertex<E>> visited) {
        visited.insertLast(v);
        System.out.print(v.getData() + " ");

        for (Edge<E> e : v.listAdj) {
            Vertex<E> neighbor = e.getRefDest();
            boolean alreadyVisited = false;
            for (Vertex<E> visitedV : visited) {
                if (visitedV.equals(neighbor)) {
                    alreadyVisited = true;
                    break;
                }
            }
            if (!alreadyVisited) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    // Representación en texto de todos los vértices del grafo
    public String toString() {
        return listVertex.toString();
    }

    // Recorrido en anchura (BFS) desde un vértice
    public void bfs(E v) {
        Vertex<E> start = getVertex(v);
        if (start == null) return;

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");

            for (Edge<E> e : current.listAdj) {
                Vertex<E> neighbor = e.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // Retorna la ruta más corta entre dos vértices usando BFS
    public ArrayList<E> bfsPath(E v, E z) {
        Vertex<E> start = getVertex(v);
        Vertex<E> goal = getVertex(z);
        if (start == null || goal == null) return null;

        Map<Vertex<E>, Vertex<E>> parentMap = new HashMap<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        Set<Vertex<E>> visited = new HashSet<>();

        visited.add(start);
        queue.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(goal)) break;

            for (Edge<E> e : current.listAdj) {
                Vertex<E> neighbor = e.getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parentMap.containsKey(goal)) return null;

        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = goal;
        while (current != null) {
            path.add(0, current.getData()); // Insertar al inicio
            current = parentMap.get(current);
        }
        return path;
    }

    // Inserta una arista con peso entre dos vértices grafo no dirigido
    public void insertEdgeWeight(E v, E z, int weight) {
        Vertex<E> v1 = getVertex(v);
        Vertex<E> v2 = getVertex(z);
        if (v1 != null && v2 != null && !searchEdge(v, z)) {
            v1.listAdj.insertLast(new Edge<>(v2, weight));
            v2.listAdj.insertLast(new Edge<>(v1, weight));
        }
    }

    // Devuelve el camino más corto usa BFS por defecto
    public ArrayList<E> shortPath(E v, E z) {
        return bfsPath(v, z);
        
        /// EJERCICIO  2 
    }
    // Verifica si el grafo es conexo usando BFS
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true; // Grafo vacío es conexo
        Set<Vertex<E>> vis = new HashSet<>();
        Queue<Vertex<E>> q = new LinkedList<>();
        q.add(listVertex.getFirst());
        vis.add(listVertex.getFirst());
        while (!q.isEmpty()) {
            for (Edge<E> e : q.poll().listAdj) {
                if (vis.add(e.getRefDest())) q.add(e.getRefDest()); // Visita nuevos nodos
            }
        }
        return vis.size() == listVertex.size(); // Compara visitados con total
    }

    // Dijkstra: camino más corto en peso entre dos vértices
    public Stack<E> dijkstra(E v, E w) {
        Vertex<E> ini = getVertex(v), fin = getVertex(w);
        if (ini == null || fin == null) return null;

        Map<Vertex<E>, Integer> d = new HashMap<>(); // Distancias mínimas
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>(); // Predecesores

        for (Vertex<E> x : listVertex) d.put(x, Integer.MAX_VALUE);
        d.put(ini, 0); // Distancia al origen es 0

        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(d::get));
        pq.add(ini);

        while (!pq.isEmpty()) {
            Vertex<E> act = pq.poll();
            for (Edge<E> e : act.listAdj) {
                Vertex<E> vec = e.getRefDest();
                int alt = d.get(act) + e.getWeight(); // Costo alternativo
                if (alt < d.get(vec)) {
                    d.put(vec, alt); // Actualiza distancia
                    prev.put(vec, act); // Guarda predecesor
                    pq.add(vec);
                }
            }
        }

        if (!d.containsKey(fin) || d.get(fin) == Integer.MAX_VALUE) return null;

        Stack<E> res = new Stack<>();
        for (Vertex<E> p = fin; p != null; p = prev.get(p)) res.push(p.getData()); // Reconstruye camino
        Stack<E> camino = new Stack<>();
        while (!res.isEmpty()) camino.push(res.pop()); // Invierte camino
        return camino;
    }


     //EJERCICIO 5
// En tu clase GraphLink<E>, implementa métodos como 
    public int gradoNodo(E v) {
        Vertex<E> vert = getVertex(v);
        return (vert != null) ? vert.listAdj.size() : -1;
    }
    
    // Verifica si el grafo tiene exactamente 2 nodos de grado 1 y todos los demás de grado 2 
    public boolean esCamino() {
        int grado1 = 0, grado2 = 0;
        for (Vertex<E> v : listVertex) {
            int grado = v.listAdj.size();
            if (grado == 1) grado1++;
            else if (grado == 2) grado2++;
            else return false;
        }
        return grado1 == 2 && (grado1 + grado2 == listVertex.size());
    }
    
    //Todos los nodos deben tener grado 2
    public boolean esCiclo() {
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != 2) return false;
        }
        return true;
    }
    
    // Los demás grado 3 conectados en ciclo + al centro
    public boolean esRueda() {
        int centro = 0, periferia = 0;
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            int g = v.listAdj.size();
            if (g == n - 1) centro++;
            else if (g == 3) periferia++;
            else return false;
        }
        return centro == 1 && (centro + periferia == n);
    }
    
    //Todos los vértices tienen grado n - 1
    public boolean esCompleto() {
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != n - 1) return false;
        }
        return true;
    }

  // EJERCICIO  6 
    
    public void mostrarMatrizAdyacencia() {
        int n = listVertex.size();
        ArrayList<Vertex<E>> vertices = new ArrayList<>();
        for (Vertex<E> v : listVertex) vertices.add(v);

        System.out.print("   ");
        for (int i = 0; i < n; i++) System.out.print(vertices.get(i).getData() + " ");
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(vertices.get(i).getData() + ": ");
            for (int j = 0; j < n; j++) {
                boolean conectado = false;
                for (Edge<E> e : vertices.get(i).listAdj) {
                    if (e.getRefDest().equals(vertices.get(j))) {
                        conectado = true;
                        break;
                    }
                }
                System.out.print((conectado ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    
    // EJERCICIO 7 
 // Grado de salida cantidad de aristas salientes
    public int gradoSalida(E v) {
        Vertex<E> vert = getVertex(v);
        return (vert != null) ? vert.listAdj.size() : -1;
    }

    // Grado de entrada contar cuántos apuntan a él
    public int gradoEntrada(E v) {
        Vertex<E> target = getVertex(v);
        int count = 0;
        for (Vertex<E> vtx : listVertex) {
            for (Edge<E> e : vtx.listAdj) {
                if (e.getRefDest().equals(target)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Verifica si el grafo es un camino dirigido (inicio -> ... -> fin)
    public boolean esCaminoDirigido() {
        int inicio = 0, fin = 0;
        for (Vertex<E> v : listVertex) {
            int in = 0, out = v.listAdj.size(); // Entradas y salidas
            for (Vertex<E> u : listVertex) {
                if (!u.equals(v)) {
                    for (Edge<E> e : u.listAdj) {
                        if (e.getRefDest().equals(v)) in++; // Cuenta entradas
                    }
                }
            }

            // Clasifica nodo según entradas/salidas
            if (in == 0 && out == 1) inicio++;
            else if (in == 1 && out == 0) fin++;
            else if (in == 1 && out == 1) continue;
            else return false; // No cumple condición de camino
        }
        return inicio == 1 && fin == 1; // Debe tener solo un inicio y un fin
    }

    // Verifica si el grafo es un ciclo dirigido
    public boolean esCicloDirigido() {
        for (Vertex<E> v : listVertex) {
            int in = 0, out = v.listAdj.size(); // Entradas y salidas
            for (Vertex<E> u : listVertex) {
                for (Edge<E> e : u.listAdj) {
                    if (e.getRefDest().equals(v)) in++;
                }
            }
            if (in != 1 || out != 1) return false; // Cada nodo debe tener 1 entrada y 1 salida
        }
        return true;
    }

    // Verifica si el grafo dirigido es completo
    public boolean esCompletoDirigido() {
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != n - 1) return false; // Cada vértice debe apuntar a todos menos a sí mismo
        }
        return true;
    }

// EJERCICIO 8
    // vértices y aristas con notación matemática 
    public void representacionFormal() {
        System.out.println("Vértices {A, B, C, D}");
        System.out.println("Aristas {(A,B), (A,C), (B,D), (C,D)}");
    }

}


