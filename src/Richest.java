//Written by Brendon Kendall
//2/21/2023


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Richest {

    public static void main(String[] args) throws FileNotFoundException {
        //The first arg should be the name of the file, and the 2nd arg should be the order
        int[] data = fillArrayFromFile(args[0]);
        System.out.println(data.length);

        BTree2 tree = new BTree2(3);


    }

    public static int[] fillArrayFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        ArrayList<Integer> tempArray = new ArrayList<>();

        //Reads and parses each line in the file as an Integer and adds it to the temp arraylist
        while (scanner.hasNext()) {
            tempArray.add(Integer.parseInt(scanner.next()));
        }

        //converts the ArrayList to a primitive array of ints, then returns it.
        return tempArray.stream().mapToInt(i -> i).toArray();
    }

    public static void fillTreeFromArray(int[] arr) {

    }






}
