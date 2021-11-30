import java.util.LinkedList;
import java.util.List;

public class Vertex<T> 
    {
        private boolean visited = false;
        private T value;
        private int index;
        private LinkedList<Vertex<T>> list; 

        Vertex(int index)
        {
            this.index = index;
        }

        Vertex(T value, int index)
        {
            this(index);
            this.value = value;
        }

        public List<Vertex<T>> getList()
        {
            return list;
        }

        public void setList(LinkedList<Vertex<T>> list)
        {
            while(!list.isEmpty())
            {
                this.list.add(list.pop());
            }
        }
        
        public void addToList(Vertex<T> target)
        {
            if (!list.contains(target))
                list.add(target);
        }

        public boolean removeFromList(Vertex<T> target)
        {
            return list.remove(target);
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