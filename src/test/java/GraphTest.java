public class GraphTest
{
    private void printBreadthFirst(Graph<T> graph)
    {
        T[] traversalArray = graph.breadthFirstTraverse().toArray();
        System.out.println("Breadth-First Traversal: " + traversalArray.toString());
    }

    private void printDepthFirst(Graph<T> graph)
    {
        T[] traversalArray = graph.depthFirstTraverse().toArray();
        System.out.println("Depth-First Traversal: " + traversalArray.toString());
    }
    
    public static void main(String[] args)
    {
        char [] V = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        boolean [][] E = {{false, true, false, true, true, false, false, false, false},
                        {false, false, false, false, true, false, false, false, false},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, true, false, false}, 
                        {false, false, false, false, false, true, false, true, false},
                        {false, false, true, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, false, true},
                        {false, false, false, false, false, true, false, false, false}};

        AdjacencyMatrixGraph <char> matrixGraph = new AdjacencyMatrixGraph<>(V, E);
        System.out.println("Traversal under Adjacency Matrix Graph Implementation:")
        printBreadthFirst(matrixGraph);
        printDepthFirst(matrixGraph);
    }
}