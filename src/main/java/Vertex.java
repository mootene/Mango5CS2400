public class Vertex<T> 
    {
        private boolean visited = false;
        private T value;
        private int index;

        Vertex(int index)
        {
            this.index = index;
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