public class MinHeap {

    private int[] heapArray;
    public int size;
    public int maxSize;

    //MinHeap starts indexing at 1
    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heapArray = new int[this.maxSize + 1];
    }

    public int parent(int i){
        return i / 2;
    }

    public int leftChild(int i){
        return i * 2;
    }

    public int rightChild(int i){ return (i * 2) + 1; }

    public boolean isLeaf(int pos) {
        if (pos > (this.size / 2) && pos <= this.size) {
            return true;
        }
        return false;
    }

    //This is a dumb insertion. Does NOT preserve integrity of the heap
    public void insertToEnd(int data) {
        heapArray[this.size + 1] = data;
        size++;
    }

    //Smart insertion. Preserves integrity of the heap
    public void insert(int data) {
        heapArray[this.size] = data;

        int cur = this.size;
        while (heapArray[cur] > heapArray[parent(cur)]) {
            swap(cur, parent(cur));
            cur = parent(cur);
        }
        this.size++;
    }

    public void swap(int firstIndex, int secondIndex) {
        int temp = heapArray[firstIndex];
        heapArray[firstIndex] = heapArray[secondIndex];
        heapArray[secondIndex] = temp;
    }

    public void minHeapify(int[] heap, int i, int heapSize) {
        int l = leftChild(i);
        int r = rightChild(i);
        int smallest;
        if(l <= heapSize && heap[l] < heap[i]){
            smallest = l;
        } else {
            smallest = i;
        }
        if(r <= heapSize && heap[r] < heap[smallest]){
            smallest = r;
        }
        if(smallest != i){
            swap(i, smallest);
            minHeapify(heap, smallest, heapSize);
        }
    }

    public void buildMinHeap(int[] heap) {
        for (int i = this.size / 2; i >= 1; i--){
            minHeapify(heap, i, this.size - 1);
        }
    }

    public void heapSort(int[] heap) {
        buildMinHeap(heap);
        int heapSize = heap.length-1;
        for(int i = heap.length-1; i >= 2; i--){
            int temp = heap[1];
            heap[1] = heap[i];
            heap[i] = temp;
            heapSize--;
            minHeapify(heap, 1, heapSize);
        }
    }


}
