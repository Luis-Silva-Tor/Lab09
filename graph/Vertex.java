package graph;

import listlinked.ListLinked;

//Clase genérica que representa un vértice en un grafo.
 
public class Vertex<E> {
    private E data;  // Dato del vértice
    protected ListLinked<Edge<E>> listAdj;  // Lista de adyacencia (aristas conectadas)

    // Constructor: crea un vértice con el dato especificado.
   
    public Vertex(E data) {
        this.data = data;
        listAdj = new ListLinked<Edge<E>>();
    }

    //etorna el dato del vértice.
      
  
    public E getData() {
        return data;
    }

 
     //Compara si dos vértices son iguales basándose en su dato.
     
    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    //Representación en texto del vértice y sus adyacencias.
     
    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}
