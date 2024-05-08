import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.xml.crypto.Data;
import java.io.FileNotFoundException;


public class DateApp {
    public static void main(String[] args) {
        if (args[0].endsWith(".txt")){
            DateApp x = new DateApp();
            x.txtReader(args);
        }
        else{
            DateApp x = new DateApp();
            x.date(args);
        }
    }

    public void txtReader(String[] args){       //Command line .txt
        try{
            //Working out number of lines
            BufferedReader rdrCount = new BufferedReader(new FileReader(args[0]));  
            int lines = 0;
            while (rdrCount.readLine() != null) {           
                lines++;
            }
            String[] dateArray = new String[lines];
            rdrCount.close();

            //Adding the lines to a String Array
            BufferedReader rdr = new BufferedReader(new FileReader(args[0]));
            String line;
            int i = 0;

            while ((line = rdr.readLine())!= null){
                dateArray[i] = line;
                i++;
            }
            System.out.println(Assignment1.printDates(dateArray));
            rdr.close();
        }
        catch(IOException io){
            System.out.println(io);
        }
    }

    public void date(String[] args){            //Command line dates
        SimpleDate argsDate = new SimpleDate(args[0]);
        System.out.println(argsDate.toString());
        //System.out.print(args);
        /*
        SimpleDate date1 = new SimpleDate(2003,3,13);
        SimpleDate date2 = new SimpleDate(2022,4,14);
        SimpleDate date3 = new SimpleDate(2022,3,4);

        System.out.println("Date1: " + date1.toString());
        System.out.println("Date2: " + date2.toString());
        System.out.println("Date3: " + date3.toString());
         */
        /*
        System.out.println("Day of week for Date1: " + Assignment1.dayOfWeek(date1));
        System.out.println("Day of week for Date2: " + Assignment1.dayOfWeek(date2));
        System.out.println("Day of week for Date3: " + Assignment1.dayOfWeek(date3));

        String dayOfWeek = "Thu";
        SimpleDate[] dates = new SimpleDate[3];
        dates[0] = new SimpleDate(2003,3,13);
        dates[1] = new SimpleDate(2022,4,14);
        dates[2] = new SimpleDate(2022,3,4);

        System.out.println("Amount of"+dayOfWeek + " = " + Assignment1.countDatesOnDay(dates, dayOfWeek));
        System.out.println("Most frequent day of week: " + Assignment1.mostFrequentDayOfWeek(dates));
        */

    }

}
