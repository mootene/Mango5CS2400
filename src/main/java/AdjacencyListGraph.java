import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyListGraph <E> implements GraphInterface<E>
{
    Vertex<E>[] vertices;
   
    public AdjacencyListGraph(int n)
    {
        @SuppressWarnings("unchecked")
        Vertex<E>[] v = (Vertex<E>[]) new Vertex[n];
        vertices = v;
        for (int i = 0; i < vertices.length; i++) 
        {
            vertices[i] = new Vertex<E>(i);
        }
    }
    
    public AdjacencyListGraph(E[] labels)
    {
        this(labels.length);
        for (int i = 0; i < vertices.length; i++)
        {
            vertices[i].setValue(labels[i]);
        }
    }

    public AdjacencyListGraph(E[] labels, E[][] edges)
    {
        this(labels);
        for (int i = 0; i < edges.length; i++)
        {
            E[] pair = edges[i];
            Vertex<E> source = findVertexWithValue(pair[0]);
            source.addToList(findVertexWithValue(pair[1]));
        }
    }

    public boolean isEdge(int source, int target)
    {
        return vertices[source].getList().contains(vertices[target]);
    }

    public void addEdge(int source, int target)
    {
        vertices[source].addToList(vertices[target]);
    }

    public E getLabel(int vertex)
    {
        return vertices[vertex].getValue();
    }

    public int[] neighbors(int vertex)
    {
        List<Vertex<E>> list = vertices[vertex].getList();
        int [] neighborIndeces = new int[vertices[vertex].getList().size()];
        int i = 0;
        for (Vertex<E> v : list)
        {
            neighborIndeces[i] = v.getIndex();
            i++;
        }
        return neighborIndeces;
    }

    public int size()
    {
        return vertices.length;
    }

    public boolean removeEdge(int source, int target)
    {
        return vertices[source].removeFromList(vertices[target]);
    }

    @Override
    public void setLabel(int vertex, E newLabel)
    {
        vertices[vertex].setValue(newLabel);
    }

    private void resetVerticesVisitedState()
    {
        for (Vertex<E> v : vertices)
        {
            v.clearVisited();
        }
    }

    private Vertex<E> findVertexWithValue(E value)
    {
        for (Vertex<E> v : vertices)
        {
            if (v.getValue() == value)
                return v;
        }
        return null;
    }

    public Queue<E> breadthFirstTraverse(E originLabel)
    {
        resetVerticesVisitedState();
        LinkedList<E> traversalOrder = new LinkedList<>();
        LinkedList<Vertex<E>> vertexQueue = new LinkedList<>();
        Vertex<E> originVertex = findVertexWithValue(originLabel);
        //TODO: check for null
        originVertex.visit();
        traversalOrder.add(originVertex.getValue());
        vertexQueue.add(originVertex); 
        while (!vertexQueue.isEmpty())
        {
            Vertex<E> frontVertex = vertexQueue.poll();
            for (Vertex<E> neighbor : frontVertex.getList())
            {
                if (!neighbor.isVisited())
                {
                    neighbor.visit();
                    traversalOrder.add(neighbor.getValue());
                    vertexQueue.add(neighbor);
                }
            }
        }
        return traversalOrder;
    }

    private boolean hasUnvisitedNeighbor(Vertex<E> vertex)
    {
        return nextUnvisitedNeighbor(vertex) != null;
    }
    
    private Vertex<E> nextUnvisitedNeighbor(Vertex<E> vertex)
    {
        List<Vertex<E>> list = vertex.getList();
        for (Vertex<E> v : list)
        {
            if (!v.isVisited())
                return v; 
        }
        return null;
    }

    public Queue<E> depthFirstTraverse(E originLabel)
    {
        resetVerticesVisitedState();
        LinkedList<E> traversalOrder = new LinkedList<>();
        Stack<Vertex<E>> vertexStack = new Stack<>();
        Vertex<E> originVertex = findVertexWithValue(originLabel);
        //TODO: check for null
        originVertex.visit();
        traversalOrder.add(originVertex.getValue());
        vertexStack.push(originVertex);
        while(!vertexStack.isEmpty())
        {
            Vertex<E> topVertex = vertexStack.peek();
            if(hasUnvisitedNeighbor(topVertex))
            {
                Vertex<E> neighbor = nextUnvisitedNeighbor(topVertex);
                neighbor.visit();
                traversalOrder.add(neighbor.getValue());
                vertexStack.push(neighbor);
            }
            else
                vertexStack.pop();
        }
        return traversalOrder;
    }
}
