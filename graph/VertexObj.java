package graph;

public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public boolean equals(Object o) {
        if (o instanceof VertexObj<?, ?>) {
            VertexObj<?, ?> other = (VertexObj<?, ?>) o;
            return this.info.equals(other.info);
        }
        return false;
    }

    public String toString() {
        return info.toString();
    }
}
