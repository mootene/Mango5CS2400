import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyMatrixGraph<E> implements GraphInterface<E>
{
    private boolean[][] edges; //edges[i][j] is true if there is a vertex from i to j
    private Vertex<E>[] vertices; //labels[i] contains label for vertex i

    public AdjacencyMatrixGraph(int n)
    {
        edges = new boolean[n][n];
        @SuppressWarnings("unchecked")
        Vertex<E>[] v = (Vertex<E>[]) new Vertex[n];
        vertices = v;
        
        for (int i = 0; i < vertices.length; i++) 
        {
            vertices[i] = new Vertex<E>(i);
        }
    }

    public AdjacencyMatrixGraph(E[] labels)
    {
        this(labels.length);
        for (int i = 0; i < labels.length; i++)
        {
            vertices[i].setValue(labels[i]);
        }
    }

    public AdjacencyMatrixGraph(E[] labels, boolean[][] edges)
    {
        this(labels);
        for (int i = 0; i < labels.length; i ++)
        {
            for (int j = 0; j < labels.length; j++)
            {
                this.edges[i][j] = edges[i][j];
            }
        }
    }

    public boolean isEdge(int source, int target)
    {
        return (edges[source][target]);
    }

    public void addEdge(int source, int target)
    {
        edges[source][target] = true; 
    }

    public E getLabel(int vertex)
    {
        return vertices[vertex].getValue();
    }

    public int[] neighbors(int vertex)
    {
        int count = 0;
        for (int i = 0; i < vertices.length; i++)
        {
            if (edges[vertex][i])
                count++;
        }
        int[] neighbors = new int[count];
        count = 0;
        for (int i = 0; i < vertices.length; i++)
        {
            if (edges[vertex][i])
                neighbors[count++] = i;
        }
        return neighbors;
    }

    public int size()
    {
        return vertices.length;
    }

    public boolean removeEdge(int source, int target)
    {
        boolean edge = edges[source][target];
        edges[source][target] = false;
        return edge; 
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
            for (int i = 0; i < vertices.length; i++)
            {
                Vertex<E> neighbor = edges[frontVertex.getIndex()][i] ? vertices[i] : null;
                if (neighbor != null && !neighbor.isVisited())
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
        for (int i = 0; i < vertices.length; i++)
        {
            if (edges[vertex.getIndex()][i] && !vertices[i].isVisited())
                return vertices[i];
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

