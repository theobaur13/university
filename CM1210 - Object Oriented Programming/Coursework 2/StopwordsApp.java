/*21050251*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StopwordsApp {
    public static void main(String[] args)throws FileNotFoundException{                 //main method to choose which methods to execute
        ArrayList inputList = sortAssignment.wordReader("input.txt");
        ArrayList stopwordList = sortAssignment.wordReader("stopwords.txt");
        
        System.out.println("Q1:");
        ArrayList x = sortAssignment.deleteStopwords(inputList,stopwordList);
        System.out.println(x);

        System.out.println("Q2:");
        System.out.println(sortAssignment.insertionSort(x));

        System.out.println("Q3:");
        System.out.println(sortAssignment.mergeSort(x));

        System.out.println("Q4:");
        sortAssignment.sortPerformance();
    }
}
