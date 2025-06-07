package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
            vOri.listAdj.insertLast(new Edge<E>(vDes));
            vDes.listAdj.insertLast(new Edge<E>(vOri)); // Grafo no dirigido
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
    }
}
