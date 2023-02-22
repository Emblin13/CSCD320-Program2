import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

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
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest;
        if(left <= heapSize && heap[left] < heap[i]) {
            smallest = left;
        } else {
            smallest = i;
        }
        if(right <= heapSize && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if(smallest != i) {
            swap(i, smallest);
            minHeapify(heap, smallest, heapSize);
        }
    }

    public void buildMinHeap() {
        for (int i = this.size / 2; i >= 1; i--){
            minHeapify(this.heapArray, i, this.size - 1);
        }
    }

    //Walks through a file, replacing the root with any larger numbers it finds
    public void replaceHeapFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));

        while (scanner.hasNextInt()) {
            int next = Integer.parseInt(scanner.nextLine());
            if(next > heapArray[1]){
                heapArray[1] = next;
                minHeapify(heapArray, 1, this.size - 1);
                System.out.println("Heap is min heap: " + isMinHeap(heapArray));
            }
        }
    }

    public boolean isMinHeap(int[] heap){
        for(int i = 1; i < heap.length/2; i++){
            if(heap[i] > heap[leftChild(i)] || heap[i] > heap[rightChild(i)]){
                return false;
            }
        }
        return true;
    }

    public void heapSort() {
        buildMinHeap();
        int heapSize = this.size - 1;
        for(int i = this.size - 1; i >= 2; i--) {
            swap(1, i);
            heapSize--;
            minHeapify(heapArray, 1, heapSize);
        }
    }

    public void outputHeap(String fileName) {
        Scanner scanner = new Scanner(new FileReader(fileName));

        //Write the heap to the output file.
        File output = new File("output.txt");
        FileWriter writer = new FileWriter(output);
        for(int i = 1; i < this.size; i++){
            writer.write(heap[i] + "\n");
        }

        //Close the files.
        scanner.close();
        writer.close();
    }

}
