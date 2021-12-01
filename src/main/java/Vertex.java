import java.util.LinkedList;
import java.util.List;

public class Vertex<T> 
    {
        private boolean visited = false;
        private T value;
        private int index;
        private LinkedList<Vertex<T>> list = new LinkedList<>(); 

        Vertex(int index)
        {
            this.index = index;
        }

        Vertex(T value, int index)
        {
            this(index);
            this.value = value;
        }

        /**
         * retrieves private list variable
         * @return this object's linked list
         */
        public LinkedList<Vertex<T>> getList()
        {
            return list;
        }

        /**
         * fills this object's linked list 
         * from given linked list of vertex objects
         * @param list list of vertex objects to be copied 
         */
        public void setList(LinkedList<Vertex<T>> list)
        {
            for (Vertex<T> v : list) {
                this.list.add(v);
            }
        }
        
        /**
         * adds vertex to this object's list of edge connections, 
         * making this object a neighbor of the target
         * @param target vertex to be added to adjacecy list
         */
        public void addToList(Vertex<T> target)
        {
            if (!list.contains(target))
                list.add(target);
        }

        /**
         * removes given vertex from adjacency list, returns boolean descriptor
         * @param target vertex to be removed
         * @return true if found in list, false if vertex was never in the adjacency list to begin with
         */
        public boolean removeFromList(Vertex<T> target)
        {
            return list.remove(target);
        }

        /**
         * retrieve index variable
         * @return index of this vertex
         */
        public int getIndex()
        {
            return index;
        }

        /**
         * checks visited state of this vertex
         * @return true if marked visited, false otherwise
         */
        public boolean isVisited()
        {
            return visited;
        }

        /**
         * sets true visited state for this vertex
         */
        public void visit()
        {
            visited = true;
        }

        /**
         * clears visited state of this vertex
         */
        public void clearVisited()
        {
            visited = false;
        }

        /**
         * sets label for this vertex
         * @param value the new label for this vertex
         */
        public void setValue(T value)
        {
            this.value = value;
        }

        /**
         * retrieve's label of this vertex
         * @return this vertex's label
         */
        public T getValue()
        {
            return value;
        }
    }