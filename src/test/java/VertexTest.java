import java.beans.Transient;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
    void testGetList()
    {
        Vertex<String> v1 = new Vertex<>("hi", 1);
        Vertex<String> v2 = new Vertex<>("i'm", 2);
        Vertex<String> v3 = new Vertex<>("maya", 3);
        Vertex<String> v = new Vertex<>(0);
        v.addToList(v1);
        v.addToList(v2);
        v.addToList(v3);
        LinkedList<Vertex<String>> list = v.getList();
        assertEquals(v1, list.pop());
        assertEquals(v2, list.pop());
        assertEquals(v3, list.pop());
        assertTrue(list.isEmpty());
    }

    @Test
    void testSetList()
    {
        Vertex<String> v1 = new Vertex<>("a", 1);
        Vertex<String> v2 = new Vertex<>("c", 2);
        Vertex<String> v = new Vertex<>("!", 0);
        LinkedList<Vertex<String>> list = new LinkedList<>();
        list.add(v1);
        list.add(v2);
        v.setList(list);
        LinkedList<Vertex<String>> actual = v.getList();
        assertEquals(list.pop(), actual.pop());
    }

    @Test
    void testAddToList()
    {
        Vertex<String> v2 = new Vertex<>("two", 0);
        Vertex<String> v = new Vertex<>("!", 0);
        v.addToList(v2);
        LinkedList<Vertex<String>> list = v.getList();
        Vertex<String> actual = list.pop();
        assertEquals(v2, actual);
    }

    @Test
    void testRemoveFromList()
    {
        Vertex<String> v1 = new Vertex<>("hi", 1);
        Vertex<String> v2 = new Vertex<>("i'm", 2);
        Vertex<String> v3 = new Vertex<>("maya", 3);
        Vertex<String> v = new Vertex<>(0);
        v.addToList(v1);
        v.addToList(v2);
        v.addToList(v3);
        v.removeFromList(v2);
        LinkedList<Vertex<String>> list = v.getList();
        assertEquals(v1, list.pop());
        assertEquals(v3, list.pop());
        assertTrue(list.isEmpty());
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
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        assertFalse(v.isVisited());
    }

    @Test
    void testVisit()
    {
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        v.visit();
        assertTrue(v.isVisited());
    }

    @Test
    void testClearVisited()
    {
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        v.visit();
        v.clearVisited();
        assertFalse(v.isVisited());
    }

    @Test
    void testSetValue()
    {
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        v.setValue(54);
        assertEquals(54, v.getValue());
    }

    @Test
    void testGetValue()
    {
        Vertex <Integer> v = new Vertex<Integer>(76, 0);
        assertEquals(76, v.getValue());
    }
}