package graph;

public class Edge<E> {
    private Vertex<E> refDest;
    private int weight;

    // Constructor con peso por defecto (-1 indica sin peso)
    public Edge(Vertex<E> refDest) {
        this(refDest, -1);
    }

    // Constructor con peso definido
    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    // Getter del destino
    public Vertex<E> getRefDest() {
        return refDest;
    }

    // Getter del peso (¡necesario para Dijkstra!)
    public int getWeight() {
        return weight;
    }

    // equals para comparar aristas por destino
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    // toString para mostrar el vértice y su peso si existe
    public String toString() {
        if (weight > -1)
            return refDest.getData() + " [" + weight + "], ";
        else
            return refDest.getData() + ", ";
    }
}
