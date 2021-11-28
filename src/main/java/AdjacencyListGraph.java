public class AdjacencyListGraph <E> implements GraphInterface
{
    Vertex<E>[] vertices;
    
    public boolean isEdge(int source, int target);
    public void addEdge(int source, int target);
    public E getLabel(int vertex);
    public int[] neighbors(int vertex);
    public int size();
    public boolean removeEdge(int source, int target);
    public void setLabel(int vertex, E newLabel);
}
}