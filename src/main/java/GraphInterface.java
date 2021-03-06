import java.util.Queue;

public interface GraphInterface<E>
{
    public boolean isEdge(int source, int target);
    public void addEdge(int source, int target);
    public E getLabel(int vertex);
    public int[] neighbors(int vertex);
    public int size();
    public boolean removeEdge(int source, int target);
    public void setLabel(int vertex, E newLabel);
    public Queue<E> depthFirstTraverse(E originLabel);
    public Queue<E> breadthFirstTraverse(E originLabel);
}