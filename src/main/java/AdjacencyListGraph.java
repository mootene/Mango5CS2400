import java.util.LinkedList;

public class AdjacencyListGraph <E> implements GraphInterface
{
    Vertex<E>[] vertices;
    LinkedList<E>[] edges;

    AdjacencyListGraph(int n)
    {
        //TODO: Suppress Warnings
        Vertex<E>[] vertices = new Vertex[n];
        LinkedList<E>[] edges = new LinkedList[n];
        for (int i = 0; i < n; i++)
        {
            vertices[i] = new Vertex(i);
            edges[i] = new LinkedList<>();
        }
    }

    AdjacencyListGraph(Vertex<E>[] vertices, LinkedList<E>[] edges)
    {
        for (int i = 0; i < vertices.length; i++)
        {
            this.vertices[i] = vertices[i];
            this.edges[i] = edges[i];
        }
    }

    public boolean isEdge(int source, int target)
    {
        return edges[source].contains(target);
    }
    public void addEdge(int source, int target)
    {
        if(!isEdge(source, target))
        {
            
        }
    }

    public E getLabel(int vertex)
    {

    }

    public int[] neighbors(int vertex)
    {

    }

    public int size()
    {
        return vertices.length;
    }

    public boolean removeEdge(int source, int target)
    {

    }

    public void setLabel(int vertex, E newLabel)
    {

    }
}
