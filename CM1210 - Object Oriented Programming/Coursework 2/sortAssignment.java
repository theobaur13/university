/*21050251*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class sortAssignment {
    //variables
    static int swaps = 0;               

    //Constructor
    public sortAssignment(){}

    //Removes stopwords from list input
    public static ArrayList deleteStopwords(ArrayList input, ArrayList stopwords){
        ArrayList newList = new ArrayList<String>(input);
        for (int i = 0; i < newList.size(); i++){               //Iterates through input list 
            String index = newList.get(i).toString();
            if (stopwords.contains(index)){                     
                //System.out.println("Deleting" + index);
                newList.remove(index);                          //Removes any word that matches a stopword from input
            }
        }
        return newList;
    }

    //Method to make it easier to convert text files into ArrayLists
    public static ArrayList wordReader(String file) throws FileNotFoundException{   //Reading text files
        Scanner rdr = new Scanner(new FileReader(file));
        ArrayList<String> inputList = new ArrayList<>();
        rdr.useDelimiter("[^A-Za-z']+");                                            //Removes any unwanted characters
        while (rdr.hasNext()) {
            String word = rdr.next();
            inputList.add(word);
        }
        rdr.close();
        return inputList;
    }

    public static ArrayList insertionSort(ArrayList listOfWords){
        ArrayList newList = new ArrayList<String>(listOfWords);
        for (int i = 1; i < newList.size(); i++){                                   //Iterates through unsorted list
            String item = newList.get(i).toString().toLowerCase();                  //Converts element i in list to string and lowercase
            int j = (i-1);
            while (j >=0 && newList.get(j).toString().toLowerCase().compareTo(item) > 0){       //Checks to see if previous element is bigger than i by comparing ASCII values
                newList.set(j+1, newList.get(j).toString().toLowerCase());                      //If previous element is bigger than i, set i to previous item
                //System.out.println("Swap");                                                   //
                swaps++;
                j = j-1;
            }
            newList.set(j+1, item);                                                             //If previous item is smaller than i set i to i
            //System.out.println("Swap");                                                       //
            //swaps++;
        }
        return newList;
    }
    /*
    *Returns sorted list
    *This code was adapted from psuedocode from CM1210, Week 5 [4th March], "Lecture - Bubble sort and insertion sort", Page 19
    *accessed 07/05/2022
    *https://learn-eu-central-1-prod-fleet01-xythos.content.blackboardcdn.com/5fd150846bae0/12230479?X-Blackboard-Expiration=1651946400000&X-Blackboard-Signature=1otQcWvFCDQbttuPvzdpEummejXrXfv5O%2BcYPpAl%2FJA%3D&X-Blackboard-Client-Id=179260&response-cache-control=private%2C%20max-age%3D21600&response-content-disposition=inline%3B%20filename%2A%3DUTF-8%27%27Bubble%2520sort%2520and%2520insertion%2520sort.pdf&response-content-type=application%2Fpdf&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJHMEUCICGTrixk205bXxMi2sw00vRdiyE5Szw4Qjdk9CEEKMcyAiEAxcDOKNJu9FPaMaZP5mDfVYvJiLCJprYPMoEceaUmBmUq5QQIvf%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARACGgw2MzU1Njc5MjQxODMiDHFF%2Bze7PdvCiLD2Vyq5BAMp%2FW%2BrjMDLtqCOarxQbswGfOhc818x3uur0PtzneVcWzqIdk8MFouTbjpROlOkVTWAbh0L6yUOEbTwLdcgbZXxv1WR2BLUFPk%2FWDBjriDsb%2FdmW3N4iz2EE%2B5jFciGYz8mow2%2Bo95aivlkBZSaGnQ0XdaacZs7tWdPA3M3BR1G3xe0UiXn01f8LGbzSoTZD%2BvZfQvB4hjjm7sV%2FKPSHt8xJBxlJCstd%2FCe5qo9U9bDB2GNN%2BsASJ%2BrzCJqbP6M7M%2BmRiiOL4bHECjvJlb3Ti8wcTwAoQiKVneTE36dvKG2NXgjP0p6giBHeiAzGY5dBwj%2FwrBufNS2hLdkIyp5TPA%2BsHxWmqNSNU7JhFh8vDcnDcIEaTBJq66wMDbiJk6ah4IzIoJTPnblCUXPqMHisjijExRDHe9RJiNTTQdFBqqP3eLuqWE6oAh5ymmm3IFGic%2FEsxZhsRj6hiZTJgyC8a1bKh%2Flm9EwAQeJSq3avqoxwkyQOkw8o5OALKaRxeTZEHcfkaa09ZZIowzrUByT23FkUS7%2FjGiVckbSv2JMFHf%2FxVu2vktmnTYtMiqEJSK2rs9KjflGmCL%2B%2FtnjbwgBvQ3aZ4sVDkzOivAfndJvfu3pYCSEIAS7MNbhyMnEcO5fMwQdFVoXZTvWP0wJzLkjhYuATx91TcwggXfcslutTOmGKWEQsf9yu4fUggEJC9omhX0nK5DY5jlPbg9O0cXgd8yOE0SLoJfHH6MnDODnRM%2FlfAb4gNqOytIFMKi%2B2ZMGOqkBgOt38kmD8aeucPuVKwvMyNmDmOQ%2FJD0egtWc9spb%2FPZZWKsQqHlV%2Bd%2FXoGFIKh8QHTKtGkDjb1ZGNU1EEWToQpZvx75zYiJv5wKDbpSNInFBwJ1fPQdqR680MWt4WkzB0uXr1ev9oOCbGhdH6F%2Ba5c5MzcFBTfu4Fna%2FLQuHLUSempBk7pFE%2B%2FQ2VA7dKaZS8tGtX2HNT7q3RDSOCvcHSuIfNBYME1iDhQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220507T120000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=21600&X-Amz-Credential=ASIAZH6WM4PLTJWUWU45%2F20220507%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Signature=b0a54f59f963d5235de6f921c8187b70dfe115666b5330597334b88929d5b32f
    *
    *@param input unsorted list
    *@return sorted list
    */

    public static ArrayList mergeSort(ArrayList s){
        int p = 0;
        int r = s.size()-1;
        if (p < r){                                                                 //If index of first item is smaller than index of last item
            int q = (p+r)/2;
            ArrayList s1 = new ArrayList<>();
            for (int i = p; i <= q; i++){                                           //Divides s into two lists, s1 and s2
                s1.add(s.get(i));                                                   //Swap?
            }
            ArrayList s2 = new ArrayList<>();                                       //Divides s into two lists, s1 and s2
            for (int i = q+1; i <= r; i++){
                s2.add(s.get(i));                                                   //Swap?
            }
            mergeSort(s1);                                                          //Runs first half (s1) into mergesort until it is divided into a single value
            mergeSort(s2);                                                          //Runs second half (s2) into mergesort until it is divided into a single value
            merge(s1, s2, s);                                                       //Merges and sorts s1 and s2
        }
        return s;
    }

    /*
    *Divides list and merges sorted list
    *This code was adapted from psuedocode from CM1210, Week 6 [11th March], "Lecture - Merge sort (Divide & Conquer)", Page 15
    *accessed 07/05/2022
    *https://learn-eu-central-1-prod-fleet01-xythos.content.blackboardcdn.com/5fd150846bae0/12367435?X-Blackboard-Expiration=1651946400000&X-Blackboard-Signature=bscOOcp3vTZzm8sEwhQln9qZmLcFCJfVigOqMNoLnoA%3D&X-Blackboard-Client-Id=179260&response-cache-control=private%2C%20max-age%3D21600&response-content-disposition=inline%3B%20filename%2A%3DUTF-8%27%27Lecture%2520-%2520Merge%2520sort%2520%2528Divide%2520%2526%2520Conquer%2529.pdf&response-content-type=application%2Fpdf&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJHMEUCICGTrixk205bXxMi2sw00vRdiyE5Szw4Qjdk9CEEKMcyAiEAxcDOKNJu9FPaMaZP5mDfVYvJiLCJprYPMoEceaUmBmUq5QQIvf%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARACGgw2MzU1Njc5MjQxODMiDHFF%2Bze7PdvCiLD2Vyq5BAMp%2FW%2BrjMDLtqCOarxQbswGfOhc818x3uur0PtzneVcWzqIdk8MFouTbjpROlOkVTWAbh0L6yUOEbTwLdcgbZXxv1WR2BLUFPk%2FWDBjriDsb%2FdmW3N4iz2EE%2B5jFciGYz8mow2%2Bo95aivlkBZSaGnQ0XdaacZs7tWdPA3M3BR1G3xe0UiXn01f8LGbzSoTZD%2BvZfQvB4hjjm7sV%2FKPSHt8xJBxlJCstd%2FCe5qo9U9bDB2GNN%2BsASJ%2BrzCJqbP6M7M%2BmRiiOL4bHECjvJlb3Ti8wcTwAoQiKVneTE36dvKG2NXgjP0p6giBHeiAzGY5dBwj%2FwrBufNS2hLdkIyp5TPA%2BsHxWmqNSNU7JhFh8vDcnDcIEaTBJq66wMDbiJk6ah4IzIoJTPnblCUXPqMHisjijExRDHe9RJiNTTQdFBqqP3eLuqWE6oAh5ymmm3IFGic%2FEsxZhsRj6hiZTJgyC8a1bKh%2Flm9EwAQeJSq3avqoxwkyQOkw8o5OALKaRxeTZEHcfkaa09ZZIowzrUByT23FkUS7%2FjGiVckbSv2JMFHf%2FxVu2vktmnTYtMiqEJSK2rs9KjflGmCL%2B%2FtnjbwgBvQ3aZ4sVDkzOivAfndJvfu3pYCSEIAS7MNbhyMnEcO5fMwQdFVoXZTvWP0wJzLkjhYuATx91TcwggXfcslutTOmGKWEQsf9yu4fUggEJC9omhX0nK5DY5jlPbg9O0cXgd8yOE0SLoJfHH6MnDODnRM%2FlfAb4gNqOytIFMKi%2B2ZMGOqkBgOt38kmD8aeucPuVKwvMyNmDmOQ%2FJD0egtWc9spb%2FPZZWKsQqHlV%2Bd%2FXoGFIKh8QHTKtGkDjb1ZGNU1EEWToQpZvx75zYiJv5wKDbpSNInFBwJ1fPQdqR680MWt4WkzB0uXr1ev9oOCbGhdH6F%2Ba5c5MzcFBTfu4Fna%2FLQuHLUSempBk7pFE%2B%2FQ2VA7dKaZS8tGtX2HNT7q3RDSOCvcHSuIfNBYME1iDhQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220507T120000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=21600&X-Amz-Credential=ASIAZH6WM4PLTJWUWU45%2F20220507%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Signature=e4b8bef9bee8e504e90f0c4ceddcb763317cdf10df73d3cad4ac4c11c84cb860
    *
    *@param input unsorted list
    */

    public static void merge(ArrayList s1, ArrayList s2, ArrayList s){
        ArrayList tempArray = new ArrayList<>();
        while (s1.size() > 0 && s2.size() > 0){                                                     //While there are elements in s1 and s2
            if (s1.get(0).toString().compareToIgnoreCase(s2.get(0).toString()) <= 0){               //Checks to see if the ASCII value of element in s1 is smaller than or equal to s2
                tempArray.add(s1.get(0));                                                           //Adds element of s1 to array of sorted words
                s1.remove(0);                                                                       //Removes element of s1 from s1
                //System.out.println("Swap");                                                       //
                swaps++;
            }
            else{
                tempArray.add(s2.get(0));                                                           //Adds element of s2 to array of sorted words
                s2.remove(0);                                                                       //Removes element of s1 from s1
                //System.out.println("Swap");                                                       //
                swaps++;
            }
        }
        while (s1.size() > 0){                                                                      //If there are elements in s1 but none in s2 add remaining items to array of sorted words
            tempArray.add(s1.get(0));
            s1.remove(0);
            //System.out.println("Swap");                                                           //
            swaps++;
        }
        while (s2.size() > 0){                                                                      //If there are elements in s2 but none in s1 add remaining items to array of sorted words
            tempArray.add(s2.get(0));
            s2.remove(0);
            //System.out.println("Swap");                                                           //
            swaps++;
        }
        for (int i = 0; i < s.size(); i++){                                                         //Iterates through s and replaces s with the sorted array
            s.set(i, tempArray.get(i));
        }
    }

    /*
    *Sorts and merges two list
    *This code was adapted from psuedocode from CM1210, Week 6 [11th March], "Lecture - Merge sort (Divide & Conquer)", Page 17
    *accessed 07/05/2022
    *https://learn-eu-central-1-prod-fleet01-xythos.content.blackboardcdn.com/5fd150846bae0/12367435?X-Blackboard-Expiration=1651946400000&X-Blackboard-Signature=bscOOcp3vTZzm8sEwhQln9qZmLcFCJfVigOqMNoLnoA%3D&X-Blackboard-Client-Id=179260&response-cache-control=private%2C%20max-age%3D21600&response-content-disposition=inline%3B%20filename%2A%3DUTF-8%27%27Lecture%2520-%2520Merge%2520sort%2520%2528Divide%2520%2526%2520Conquer%2529.pdf&response-content-type=application%2Fpdf&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEPT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJHMEUCICGTrixk205bXxMi2sw00vRdiyE5Szw4Qjdk9CEEKMcyAiEAxcDOKNJu9FPaMaZP5mDfVYvJiLCJprYPMoEceaUmBmUq5QQIvf%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARACGgw2MzU1Njc5MjQxODMiDHFF%2Bze7PdvCiLD2Vyq5BAMp%2FW%2BrjMDLtqCOarxQbswGfOhc818x3uur0PtzneVcWzqIdk8MFouTbjpROlOkVTWAbh0L6yUOEbTwLdcgbZXxv1WR2BLUFPk%2FWDBjriDsb%2FdmW3N4iz2EE%2B5jFciGYz8mow2%2Bo95aivlkBZSaGnQ0XdaacZs7tWdPA3M3BR1G3xe0UiXn01f8LGbzSoTZD%2BvZfQvB4hjjm7sV%2FKPSHt8xJBxlJCstd%2FCe5qo9U9bDB2GNN%2BsASJ%2BrzCJqbP6M7M%2BmRiiOL4bHECjvJlb3Ti8wcTwAoQiKVneTE36dvKG2NXgjP0p6giBHeiAzGY5dBwj%2FwrBufNS2hLdkIyp5TPA%2BsHxWmqNSNU7JhFh8vDcnDcIEaTBJq66wMDbiJk6ah4IzIoJTPnblCUXPqMHisjijExRDHe9RJiNTTQdFBqqP3eLuqWE6oAh5ymmm3IFGic%2FEsxZhsRj6hiZTJgyC8a1bKh%2Flm9EwAQeJSq3avqoxwkyQOkw8o5OALKaRxeTZEHcfkaa09ZZIowzrUByT23FkUS7%2FjGiVckbSv2JMFHf%2FxVu2vktmnTYtMiqEJSK2rs9KjflGmCL%2B%2FtnjbwgBvQ3aZ4sVDkzOivAfndJvfu3pYCSEIAS7MNbhyMnEcO5fMwQdFVoXZTvWP0wJzLkjhYuATx91TcwggXfcslutTOmGKWEQsf9yu4fUggEJC9omhX0nK5DY5jlPbg9O0cXgd8yOE0SLoJfHH6MnDODnRM%2FlfAb4gNqOytIFMKi%2B2ZMGOqkBgOt38kmD8aeucPuVKwvMyNmDmOQ%2FJD0egtWc9spb%2FPZZWKsQqHlV%2Bd%2FXoGFIKh8QHTKtGkDjb1ZGNU1EEWToQpZvx75zYiJv5wKDbpSNInFBwJ1fPQdqR680MWt4WkzB0uXr1ev9oOCbGhdH6F%2Ba5c5MzcFBTfu4Fna%2FLQuHLUSempBk7pFE%2B%2FQ2VA7dKaZS8tGtX2HNT7q3RDSOCvcHSuIfNBYME1iDhQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220507T120000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=21600&X-Amz-Credential=ASIAZH6WM4PLTJWUWU45%2F20220507%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Signature=e4b8bef9bee8e504e90f0c4ceddcb763317cdf10df73d3cad4ac4c11c84cb860
    *
    *@param input unsorted list 1, input unsorted list 2, input unsorted list
    */    

    public static void sortPerformance()throws FileNotFoundException{
        ArrayList inputList = sortAssignment.wordReader("input.txt");                              
        ArrayList stopwordList = sortAssignment.wordReader("stopwords.txt");                       
        ArrayList masterList = sortAssignment.deleteStopwords(inputList,stopwordList);              //Initializes word list, removing any stopwords
        ArrayList oneHundredList = new ArrayList<>(masterList.subList(0, 99));                      //Initializes word list with appropriate number of words for testing
        ArrayList twoHundredList = new ArrayList<>(masterList.subList(0, 199));
        ArrayList fiveHundredList = new ArrayList<>(masterList.subList(0, 499));

        swaps = 0;
        //Tests time taken and number of swaps for each word list using both insertion sort and merge sort
        System.out.println("Insertion sort time, 100 words: "+ sortTimesInsertionSort(oneHundredList) + " nanoseconds");
        System.out.println("Insertion sort moves/swaps, 100 words: " + swaps); swaps = 0;
        System.out.println();
        System.out.println("Merge sort time, 100 words: "+ sortTimesMergeSort(oneHundredList) + " nanoseconds");
        System.out.println("Merge sort moves/swaps, 100 words: " + swaps); swaps = 0;
        System.out.println();
        System.out.println("Insertion sort time, 200 words: "+ sortTimesInsertionSort(twoHundredList) + " nanoseconds");
        System.out.println("Insertion sort moves/swaps, 200 words: " + swaps); swaps = 0;
        System.out.println();
        System.out.println("Merge sort time, 200 words: "+ sortTimesMergeSort(twoHundredList) + " nanoseconds");
        System.out.println("Merge sort moves/swaps, 200 words: " + swaps); swaps = 0;
        System.out.println();
        System.out.println("Insertion sort time, 500 words: "+ sortTimesInsertionSort(fiveHundredList) + " nanoseconds");
        System.out.println("Insertion sort moves/swaps, 500 words: " + swaps); swaps = 0;
        System.out.println();
        System.out.println("Merge sort time, 500 words: "+ sortTimesMergeSort(fiveHundredList) + " nanoseconds");
        System.out.println("Merge sort moves/swaps, 500 words: " + swaps); swaps = 0;
    }

    //Measures time taken to sort words for insertion sort
    public static long sortTimesInsertionSort(ArrayList listOfWords){
        long startTime = System.nanoTime();
        insertionSort(listOfWords);
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    //Measures time taken to sort words for merge sort
    public static long sortTimesMergeSort(ArrayList listOfWords){
        long startTime = System.nanoTime();
        mergeSort(listOfWords);
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

}
