import java.util.LinkedList;
import java.beans.Transient;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.Test;

public class GraphTest
{
    @Test
    void testIsEdge()
    {

    }

    @Test
    void testAddEdge()
    {

    }

    @Test
    void testGetLabel()
    {

    }

    @Test
    void testNeighbors()
    {

    }

    @Test
    void testSize()
    {

    }

    @Test
    void testRemoveEdge()
    {

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

        LinkedList<Vertex> a = new LinkedList<>();
        A.add('B');
        A.add('D');
        A.add('E');

        LinkedList<Character> b = new LinkedList<>();
        B.add('E');
        
        LinkedList<Character> c = new LinkedList<>();
        C.add('B');

        LinkedList<Character> d = new LinkedList<>();
        D.add('G');

        LinkedList<Character> e = new LinkedList<>();
        E.add('F');
        E.add('H');

        LinkedList<Character> f = new LinkedList<>();
        E.add('C');
        E.add('H');

        LinkedList<Character> g = new LinkedList<>();
        E.add('H');

        LinkedList<Character> h = new LinkedList<>();
        E.add('I');

        LinkedList<Character> i = new LinkedList<>();
        E.add('F');

        LinkedList[] adjacencyList = {a, b, c, d, e, f, g, h, i};

        AdjacencyMatrixGraph <Character> matrixGraph = new AdjacencyMatrixGraph<>(vertexLabels, adjacencyMatrix);
        AdjacencyListGraph <Character> listGraph = new AdjacencyListGraph<>(vertexLabels, adjacencyList);
        System.out.println("Traversal under Adjacency Matrix Graph Implementation:");
        printBreadthFirst(matrixGraph);
        printDepthFirst(matrixGraph);

        System.out.println("Traversal under Adjacency List Graph Implementation:");
        printBreadthFirst(listGraph);
        printDepthFirst(listGraph);
    }
}