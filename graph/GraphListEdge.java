package graph;

import java.util.*;
import java.util.ArrayList;
import graph.Edge;


public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;
    
    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // a) insertVertex
    public void insertVertex(V data) {
        if (!searchVertex(data)) {
            secVertex.add(new VertexObj<>(data, secVertex.size()));
        }
    }

    // b) insertEdge
    public void insertEdge(V v, V z) {
        VertexObj<V, E> v1 = getVertex(v);
        VertexObj<V, E> v2 = getVertex(z);
        if (v1 != null && v2 != null && !searchEdge(v, z)) {
            secEdge.add(new EdgeObj<>(v1, v2, null, secEdge.size()));
        }
    }

    // c) searchVertex
    public boolean searchVertex(V data) {
        return getVertex(data) != null;
    }

    // d) searchEdge
    public boolean searchEdge(V v, V z) {
        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.endVertex1.info.equals(v) && edge.endVertex2.info.equals(z)) ||
                (edge.endVertex1.info.equals(z) && edge.endVertex2.info.equals(v))) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar: obtener vértice por dato
    private VertexObj<V, E> getVertex(V data) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.info.equals(data)) return vertex;
        }
        return null;
    }

    // e) BFS
    public void bfs(V startData) {
        VertexObj<V, E> start = getVertex(startData);
        if (start == null) return;

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.print(current.info + " ");

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.endVertex1.equals(current)) {
                    neighbor = edge.endVertex2;
                } else if (edge.endVertex2.equals(current)) {
                    neighbor = edge.endVertex1;
                }

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
