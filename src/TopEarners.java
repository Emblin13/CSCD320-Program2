import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

    public class TopEarners {
        private final File output = new File("output.txt");
        private FileWriter writer;
        private int [] heap;

        public void process(File fileName) throws IOException {
            Scanner in = new Scanner(Path.of(fileName.getPath()));
            int last = 0;
            heap = new int[10001];

            //Grabbing the first 10000 numbers.
            for(int i = 1; i < heap.length; i++){
                String current = in.nextLine();
                System.out.println(current);
                heap[i] = Integer.parseInt(current);
            }

            //Building the heap.
            buildMinHeap(heap);


            //Compare the rest of the numbers to the root of the heap.
            while (in.hasNextInt()){
                int next = Integer.parseInt(in.nextLine());
                if(next > heap[1]){
                    heap[1] = next;
                    minHeapify(heap, 1, heap.length-1);
                    System.out.println("Heap is min heap: " + isMinHeap(heap));
                }
            }

            //Sort the heap.
            heapSort(heap);

            //Write the heap to the output file.
            writer = new FileWriter(output);
            for(int i = 1; i < heap.length; i++){
                writer.write(heap[i] + "\n");
            }

            //Close the files.
            in.close();
            writer.close();
        }

        public int parent(int i){
            return i/2;
        }

        public int left(int i){
            return 2*i;
        }

        public int right(int i){
            return 2*i+1;
        }

        public void minHeapify(int[] heap, int i, int heapSize){
            int l = left(i);
            int r = right(i);
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
                int temp = heap[i];
                heap[i] = heap[smallest];
                heap[smallest] = temp;
                minHeapify(heap, smallest, heapSize);
            }
        }

        public void buildMinHeap(int[] heap){
            for(int i = heap.length/2; i >= 1; i--){
                minHeapify(heap, i, heap.length-1);
            }
        }

        public void heapSort(int[] heap){
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

        public boolean isMinHeap(int[] heap){
            for(int i = 1; i < heap.length/2; i++){
                if(heap[i] > heap[left(i)] || heap[i] > heap[right(i)]){
                    return false;
                }
            }
            return true;
        }

    }
