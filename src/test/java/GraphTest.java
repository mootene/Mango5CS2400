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
import java.lang.invoke.StringConcatException;
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

    // Utility constructors to allow tests to be parametrized:
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


    static String[] vertexLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    //graph description from project assignment:
    static String[][] edges = {{"A", "B"}, {"A", "D"}, {"A", "E"}, {"B", "E"}, 
                    {"D", "G"}, {"E", "F"}, {"E", "F"}, {"E", "H"}, {"G", "H"}, 
                    {"F", "C"}, {"F", "H"}, {"H", "I"}, {"C", "B"}, {"I", "F"}};

    static String expectedBreadthFirstOutput = "A B D E G F H C I ";
    static String expectedDepthFirstOutput = "A B E F C H I D G ";

    @Test
    void project5MatrixTestDepthFirst() 
    {
        String output = project5MatrixDepthFirst();
        assertEquals(output, expectedDepthFirstOutput);
    }

    String project5MatrixDepthFirst() 
    {
        AdjacencyMatrixGraph <String> matrixGraph = new AdjacencyMatrixGraph<>(vertexLabels, edges);
        Queue<String> traversalOrder = matrixGraph.depthFirstTraverse("A");
        StringBuilder output = new StringBuilder();
        for (String s : traversalOrder)
        {
            output.append(s);
            output.append(" ");
        }
        return output.toString();
    }

    @Test
    void project5MatrixTestBreadthFirst()
    {
        String output = project5MatrixBreadthFirst();
        assertEquals(output, expectedBreadthFirstOutput);
    }

    String project5MatrixBreadthFirst() 
    {
        AdjacencyMatrixGraph <String> matrixGraph = new AdjacencyMatrixGraph<>(vertexLabels, edges);
        Queue<String> traversalOrder = matrixGraph.breadthFirstTraverse("A");
        StringBuilder output = new StringBuilder();
        for (String s : traversalOrder)
        {
            output.append(s);
            output.append(" ");
        }
        return output.toString();
    }

    @Test
    void project5ListTestDepthFirst()
    {
        String output = project5ListDepthFirst();
        assertEquals(output, expectedDepthFirstOutput);
    }

    String project5ListDepthFirst() 
    {
        AdjacencyListGraph <String> matrixGraph = new AdjacencyListGraph<>(vertexLabels, edges);
        Queue<String> traversalOrder = matrixGraph.depthFirstTraverse("A");
        StringBuilder output = new StringBuilder();
        for (String s : traversalOrder)
        {
            output.append(s);
            output.append(" ");
        }
        return output.toString();
    }

    @Test
    void project5ListTestBreadthFirst() 
    {
        String output = project5ListBreadthFirst();
        assertEquals(output, expectedBreadthFirstOutput);
    }

    String project5ListBreadthFirst() 
    {
        AdjacencyListGraph <String> matrixGraph = new AdjacencyListGraph<>(vertexLabels, edges);
        Queue<String> traversalOrder = matrixGraph.breadthFirstTraverse("A");
        StringBuilder output = new StringBuilder();
        for (String s : traversalOrder)
        {
            output.append(s);
            output.append(" ");
        }
        return output.toString();
    }
    
    public static void main(String[] args)
    {
        GraphTest test = new GraphTest();

        System.out.println("Traversal under Adjacency Matrix Graph Implementation:");
        System.out.println("Breadth-First Traversal: " + test.project5MatrixBreadthFirst());
        System.out.println("Depth-First Traversal  : " + test.project5MatrixDepthFirst());

        System.out.println("\nTraversal under Adjacency List Graph Implementation:");
        System.out.println("Breadth-First Traversal: " + test.project5ListBreadthFirst());
        System.out.println("Depth-First Traversal  : " + test.project5ListDepthFirst());
    
    }

}