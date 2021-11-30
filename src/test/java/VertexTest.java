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

public class VertexTest
{
    @Test
    void testConstructors()
    {
        Vertex <Integer> v = new Vertex<Integer>(5, 0);
        Vertex <Integer> vx = new Vertex<Integer>(0);
        assertEquals(5, v.getValue());
        assertEquals(0, vx.getIndex());
        assertEquals(null, vx.getValue());
    }

    @Test
    void testgetIndex()
    {
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        Vertex <Integer> vx = new Vertex<Integer>(84, 5);
        assertFalse(v.getIndex() == vx.getIndex());
        assertEquals(0, v.getIndex());
    }

    @Test
    void testIsVisited()
    {
        
    }

    @Test
    void testVisit()
    {
        
    }

    @Test
    void testClearVisited()
    {
        
    }

    @Test
    void testSetValue()
    {
        
    }

    @Test
    void testGetValue()
    {
        
    }
}