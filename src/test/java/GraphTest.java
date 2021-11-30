import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;

public class GraphTest
{
    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testIsEdge()
    {
        int[] labels = {1, 2, 3, 17};
        int[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        GraphInterface<Integer> graph = new GraphInterface<>(labels, edges);
        assertTrue(graph.isEdge(0,1));
        assertFalse(17, graph.getLabel(3, 1));

    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testAddEdge()
    {
        int[] labels = {1, 2, 3, 17};
        int[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        GraphInterface<Integer> graph = new GraphInterface<>(labels, edges);        assertFalse(graph.isEdge(0, 1));
        graph.addEdge(0, 1);
        assertTrue(graph.isEdge(0, 1));
        
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testGetLabel()
    {
        int[] labels = {1, 2, 3, 17};
        GraphInterface<Integer> graph = new GraphInterface<>(labels);
        assertEquals(1, graph.getLabel(0));
        assertEquals(17, graph.getLabel(3));
    }

    @Test
    void testNeighbors()
    {

    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testSize()
    {
        int[] labels = {1, 2, 3, 17};
        Graph<Integer> graph = new Graph<>(labels);
        assertEquals(4, graph.size());
    }

    @Test
    void testRemoveEdge()
    {
        int[] labels = {1, 2, 3, 17};
        int[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        GraphInterface<Integer> graph = new GraphInterface<>(labels, edges);        assertFalse(graph.isEdge(0, 1));
        graph.removeEdge(0, 1);
        assertFalse(graph.isEdge(0, 1));
    }

    @Test
    void testSetLabel()
    {

    }

    @Test
    void testBreadthFirstTraversal()
    {

    }

    @Test
    void testDepthFirstTraversal()
    {

    }

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
        Character[] vertexLabels = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        boolean [][] adjacencyMatrix = {{false, true, false, true, true, false, false, false, false},
                        {false, false, false, false, true, false, false, false, false},
                        {false, true, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, true, false, false}, 
                        {false, false, false, false, false, true, false, true, false},
                        {false, false, true, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, true, false},
                        {false, false, false, false, false, false, false, false, true},
                        {false, false, false, false, false, true, false, false, false}};

        Character[][] edges = {{'A', 'B'}, {'A', 'D'}, {'A', 'E'}, {'B', 'E'}, 
                        {'D', 'G'}, {'E', 'F'}, {'E', 'F'}, {'E', 'H'}, {'G', 'H'}, 
                        {'F', 'C'}, {'F', 'H'}, {'H', 'I'}, {'C', 'B'}, {'I', 'F'}};

        AdjacencyMatrixGraph <Character> matrixGraph = new AdjacencyMatrixGraph<>(vertexLabels, edges);
        AdjacencyListGraph <Character> listGraph = new AdjacencyListGraph<>(vertexLabels, edges);
        System.out.println("Traversal under Adjacency Matrix Graph Implementation:");
        printBreadthFirst(matrixGraph);
        printDepthFirst(matrixGraph);

        System.out.println("Traversal under Adjacency List Graph Implementation:");
        printBreadthFirst(listGraph);
        printDepthFirst(listGraph);
    }
}