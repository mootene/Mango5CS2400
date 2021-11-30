import java.util.LinkedList;

public class Vertex<T> 
    {
        private boolean visited = false;
        private T value;
        private int index;
        private LinkedList<Vertex> list; 

        Vertex(int index)
        {
            this.index = index;
        }

        Vertex(T value, int index)
        {
            this(index);
            this.value = value;
        }

        public LinkedList<Vertex> getList()
        {
            return list;
        }

        public void setList(LinkedList<Vertex> list)
        {
            while(!list.isEmpty())
            {
                this.list.add(list.pop());
            }
        }
        
        public void addToList(Vertex target)
        {
            if (!list.contains(target))
                list.add(target);
        }

        public int getIndex()
        {
            return index;
        }

        public boolean isVisited()
        {
            return visited;
        }

        public void visit()
        {
            visited = true;
        }

        public void clearVisited()
        {
            visited = false;
        }

        public void setValue(T value)
        {
            this.value = value;
        }

        public T getValue()
        {
            return value;
        }
    }