import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.TransferHandler.TransferSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.beans.Transient;
import java.lang.reflect.InvocationTargetException;

public class GraphTest 
{

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testCommonConstructors(Class<?> graphClass)
    {
        
        String[] labels = {"a","b","c"};
        String[][] edges = {{"a","b"}, {"b", "a"}, {"c", "a"}};
        GraphInterface<String> graph1 = constructGraph(graphClass, labels, edges);
        GraphInterface<String> graph2 = constructGraph(graphClass, labels);
        GraphInterface<String> graph3 = constructGraph(graphClass, labels.length);

        assertEquals("a", graph1.getLabel(0));
        assertTrue(graph1.isEdge(1, 0));

        assertEquals(graph1.size(), graph2.size());
        assertEquals(graph1.size(), graph3.size());

        assertEquals(null, graph3.getLabel(0));
    }

    @Test
    void testAdjacencyMatrixConstructor()
    {
        String[] labels = {"a","b","c"};
        boolean[][] edges = {{false, true, true}, {false, true, false}, {true, false, false}};
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(labels, edges);
        assertEquals(3, graph.size());
        assertEquals("b", graph.getLabel(1));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testIsEdge(Class<?> graphClass)
    {
        Integer[] labels = {1, 2, 3, 17};
        Integer[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        //GraphInterface<Integer> graph = new GraphInterface<>(labels, edges);
        GraphInterface<Integer> graph = constructGraph(graphClass, labels, edges);
        assertTrue(graph.isEdge(0, 1));
        assertFalse(graph.isEdge(3, 1));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testAddEdge(Class<?> graphClass)
    {
        Integer[] labels = {1, 2, 3, 17};
        Integer[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        GraphInterface<Integer> graph = constructGraph(graphClass, labels, edges);
        assertTrue(graph.isEdge(0, 1));
        graph.addEdge(0, 1);
        assertFalse(graph.isEdge(1, 0));
        
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testGetLabel(Class<?> graphClass)
    {
        Integer[] labels = {1, 2, 3, 17};
        GraphInterface<Integer> graph = constructGraph(graphClass, labels);
        assertEquals(1, graph.getLabel(0));
        assertEquals(17, graph.getLabel(3));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testNeighbors(Class<?> graphClass)
    {
        String[] labels = {"A", "b", "c", "d"};
        String[][] edges = {{"A", "b"}, {"A", "d"}, {"c", "b"}};
        GraphInterface<String> graph = constructGraph(graphClass, labels, edges);
        int[] neighborArray = graph.neighbors(0);
        assertEquals(2, neighborArray.length);
        assertEquals(1, neighborArray[0]);
        assertEquals(3, neighborArray[1]);
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testSize(Class<?> graphClass)
    {
        Integer[] labels = {1, 2, 3, 17};
        GraphInterface<Integer> graph = constructGraph(graphClass, labels);
        assertEquals(4, graph.size());
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testRemoveEdge(Class<?> graphClass)
    {
        Integer[] labels = {1, 2, 3, 17};
        Integer[][] edges = {{1, 2}, {1, 3}, {2, 17}};
        GraphInterface<Integer> graph = constructGraph(graphClass, labels, edges);
        assertTrue(graph.removeEdge(0, 1));
        assertFalse(graph.removeEdge(2, 1));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testSetLabel(Class<?> graphClass)
    {
        GraphInterface<Integer> graph = constructGraph(graphClass, 3);
        graph.setLabel(0, 52);
        assertEquals(52, graph.getLabel(0));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testBreadthFirstTraversal(Class<?> graphClass)
    {
        String[] labels = {"a","b","c", "d"};
        String[][] edges = {{"a","b"}, {"b", "a"}, {"b", "c"}, {"c", "a"}, {"c", "d"}, {"a", "d"}};
        GraphInterface<String> graph = constructGraph(graphClass, labels, edges);
        Queue<String> traversalOrder = graph.breadthFirstTraverse("a");
        String [] expectedOrder = {"a","b","d", "c"};
        assertFalse(traversalOrder.isEmpty());
        assertTrue(Arrays.equals(traversalOrder.toArray(), expectedOrder));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            AdjacencyListGraph.class,
            AdjacencyMatrixGraph.class})
    void testDepthFirstTraversal(Class<?> graphClass)
    {
        String[] labels = {"a","b","c", "d"};
        String[][] edges = {{"a","b"}, {"b", "a"}, {"b", "c"}, {"c", "a"}, {"c", "d"}, {"a", "d"}};
        GraphInterface<String> graph = constructGraph(graphClass, labels, edges);
        Queue<String> traversalOrder = graph.depthFirstTraverse("a");
        String [] expectedOrder = {"a", "b","c", "d"};
        assertFalse(traversalOrder.isEmpty());
        assertTrue(Arrays.equals(traversalOrder.toArray(), expectedOrder));
    }

    private static <T> void printBreadthFirst(GraphInterface<T> graph, T originLabel)
    {
        T[] traversalArray = (T[]) graph.breadthFirstTraverse(originLabel).toArray();
        System.out.println("Breadth-First Traversal: " + traversalArray.toString());
    }

    private static <T> void printDepthFirst(GraphInterface<T> graph, T originLabel)
    {
        T[] traversalArray = (T[]) graph.depthFirstTraverse(originLabel).toArray();
        System.out.println("Depth-First Traversal: " + traversalArray.toString());
    }
    
    public static void main(String[] args)
    {
        String[] vertexLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        //graph description from project assignment:
        String[][] edges = {{"A", "B"}, {"A", "D"}, {"A", "E"}, {"B", "E"}, 
                        {"D", "G"}, {"E", "F"}, {"E", "F"}, {"E", "H"}, {"G", "H"}, 
                        {"F", "C"}, {"F", "H"}, {"H", "I"}, {"C", "B"}, {"I", "F"}};

        AdjacencyMatrixGraph <String> matrixGraph = new AdjacencyMatrixGraph<>(vertexLabels, edges);
        AdjacencyListGraph <String> listGraph = new AdjacencyListGraph<>(vertexLabels, edges);
        System.out.println("Traversal under Adjacency Matrix Graph Implementation:");
        printBreadthFirst(matrixGraph, "A");
        printDepthFirst(matrixGraph, "A");

        System.out.println("Traversal under Adjacency List Graph Implementation:");
        printBreadthFirst(listGraph, "A");
        printDepthFirst(listGraph, "A");
    }

    static GraphInterface constructGraph(Class c, int a)
    {
        if (c.equals(AdjacencyListGraph.class)) {
            return new AdjacencyListGraph<>(a);
        } 
        if (c.equals(AdjacencyMatrixGraph.class)) {
            return new AdjacencyMatrixGraph<>(a);
        }
        throw new IllegalArgumentException("unexpected class type");
    }

    static GraphInterface constructGraph(Class c, Object[] a)
    {
        if (c.equals(AdjacencyListGraph.class)) {
            return new AdjacencyListGraph<>(a);
        } 
        if (c.equals(AdjacencyMatrixGraph.class)) {
            return new AdjacencyMatrixGraph<>(a);
        }
        throw new IllegalArgumentException("unexpected class type");
    }

    static GraphInterface constructGraph(Class c, Object[] a, Object[][] b)
    {
        if (c.equals(AdjacencyListGraph.class)) {
            return new AdjacencyListGraph<>(a, b);
        } 
        if (c.equals(AdjacencyMatrixGraph.class)) {
            return new AdjacencyMatrixGraph<>(a, b);
        }
        throw new IllegalArgumentException("unexpected class type");
    }

}