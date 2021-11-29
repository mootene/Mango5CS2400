import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.Stack;

public class AdjacencyListGraph <E> implements GraphInterface
{
    private boolean[][] edges;
    Vertex<E>[] vertices;
    LinkedList<LinkedList> list[];
    int v;

    public AdjacencyListGraph(int v)
    {
        this.v = v;
        list = new LinkedList[v];
        for(int i = 0; i<v; i++)
        {
            list[i] = new LinkedList<>();
        }
    }

    public void printList()
    {
        for(int i=0; i<list.size(); i++)
        {
            System.out.println(i);
            for(int j=0; j<list.get(i).size(); j++)
            {
                System.out.println("->"+ list.get(i).get(j));
            }
        }
    }
    
    public boolean isEdge(int source, int target)
    {
        return (edges[source][target]);
    }
    public void addEdge(int source, int target)
    {
        list[source].addFirst(target);
        list[target].addFirst(source); 
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
            //TODO: ASK ABA
            if(neighbor != null && !neighbor.isVisited())
            {
                neighbor.visit();
                traversalOrder.add(neighbor.getLabel());
                vertexStack.push(neighbor);
            }
            else
                vertexStack.pop();
        }
        return traversalOrder;
    }
}
