//Written by Brendon Kendall
//2/21/2023


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Richest {

    public static void main(String[] args) throws IOException {
        MinHeap minHeap = new MinHeap(10000);
        //arg[0] should be the name of the file
        fillHeapFromFile(args[0], minHeap, 10000);
        System.out.println("Number of data entries: " + minHeap.size);

        //TopEarners hunterHeap = new TopEarners();
        //hunterHeap.process(new File(args[0]));


    }

    public static void fillHeapFromFile(String fileName, MinHeap heap, int lineLimit) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        //Reads and parses each line in the file as an Integer and adds it to the end of the heap
        while (scanner.hasNext() && heap.size < lineLimit) {
            int a = Integer.parseInt(scanner.next());
            heap.insertToEnd(a);
        }
    }
}
