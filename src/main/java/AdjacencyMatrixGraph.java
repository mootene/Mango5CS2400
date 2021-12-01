import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyMatrixGraph<E> implements GraphInterface<E>
{
    private boolean[][] edges; //edges[i][j] is true if there is a vertex from i to j
    private Vertex<E>[] vertices; //labels[i] contains label for vertex i

    /**
     * constructor to delare edges[][] and vertices[] given size
     * @param n given size of vertices[]
     */
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

    /**
     * calls constructor this(int) and initiates 
     * vertices with given labels array
     * @param labels array of labels for vertices
     */
    public AdjacencyMatrixGraph(E[] labels)
    {
        this(labels.length);
        for (int i = 0; i < labels.length; i++)
        {
            vertices[i].setValue(labels[i]);
        }
    }

    /**
     * constructs edges and labels given 
     * @param labels labels array to be copied into this labels
     * @param edges boolean edges array to be copied into this edges
     */
    public AdjacencyMatrixGraph(E[] labels, boolean[][] edges)
    {
        this(labels);
        for (int i = 0; i < labels.length; i ++)
        {
            for (int j = 0; j < labels.length; j++)
            {
                if (edges[i][j])
                    addEdge(i, j); // this.edges[i][j] = edges[i][j];
            }
        }
    }

    public AdjacencyMatrixGraph(E[] labels, E[][] edges)
    {
        this(labels);
        for (int i = 0; i < edges.length; i++)
        {
            E[] pair = edges[i];
            int source = findVertexWithValue(pair[0]).getIndex();
            int target = findVertexWithValue(pair[1]).getIndex();
            addEdge(source, target);
        }
    }

    /**
     * checks if an edge exists from source to target
     * * @param source source vertex index of edge (i in edges[i][j])
     * @param target target vertex index of edge (j in edges[i][j])
     */
    public boolean isEdge(int source, int target)
    {
        return (edges[source][target]);
    }

    /**
     * changes edges[source][target] to true, therefore creating a direct edge from source to target
     * @param source source vertex index of edge (i in edges[i][j])
     * @param target target vertex index of edge (j in edges[i][j])
     */
    public void addEdge(int source, int target)
    {
        edges[source][target] = true; 
    }

    /**
     * retrieves label at given vertex index in array vertices
     * @param vertex index of given vertex
     * @return label of given vertex
     */
    public E getLabel(int vertex)
    {
        return vertices[vertex].getValue();
    }

    /**
     * finds all neighbors of vertex at given index in vertices array
     * @param vertex given vertex index
     * @return array of neighbor indices
     */
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

    /**
     * retrieves size of this graph
     * @return size of this graph
     */
    public int size()
    {
        return vertices.length;
    }

    /**
     * removes edge between given source index and given target index
     * @param source index of source vertex in vertices array
     * @param target index of target vertex in vertices array
     * @return true if edge existed before removal, false if was never an edge
     */
    public boolean removeEdge(int source, int target)
    {
        boolean wasEdge = edges[source][target];
        edges[source][target] = false;
        return wasEdge; 
    }


    @Override
    /**
     * changes label of given vertex to provided value
     * @param vertex index of given vertex in vertices array
     * @param newLabel desired new label for given vertex
     */
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

    /**
     * performs breadth-first traversal on this graph. 
     * @param originLabel label of origin/starting point vertex
     * @return queue contianing order of vertices traversed
     */
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

    /**
     * performs depth first traversal of this graph from given origin vertex
     * @param originLabel label of given origin
     * @return queue of traversal order
     */
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

