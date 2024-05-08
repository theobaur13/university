import java.util.Vector;

/*
 * Name: Theodor Baur
 * Student number: 21050251
 */

public class Assignment1 {


    /*
     * A method to find the day-of-week for a date.
     *
     * Arguments:
     * `date` -- the SimpleDate for which the day-of-week is to be found.
     *
     * Return value:
     * A String representing the day of week. The day of week should be
     * expressed as a three-letter abbreviation; in other words, this method
     * returns one of:
     *   "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
     */
    public static String dayOfWeek( SimpleDate date ) {
        // YYYY/MM/DD
        String[] dayNames = {"Sat", "Sun","Mon", "Tue", "Wed", "Thu", "Fri" };

        String[] parts = date.toString().split("/");
        int y = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int d = Integer.parseInt(parts[2]);
        
        if (m < 3){
            m = m + 12;
            y--;
        }
        
        int C = Integer.parseInt(parts[0].substring(2,4));      //_ _ Y Y
        int D = Integer.parseInt(parts[0].substring(0,2));      //Y Y _ _
        
        int W = (13*(m+1))/5;
        int X = (C/4);
        int Y = (D/4);

        int Z = W + X + Y + d + C - 2 * D;
        int day = Z%7;
        if (day < 0){
            day = day + 7;
        }

        return dayNames[day];
    }




    /*
     * Given a set of dates, this method will count the number of dates in the
     * set that fall on a particular day-of-week.
     *
     * Arguments:
     * `dates` -- an array of SimpleDate objects
     * `dayOfWeek` -- a String representing the day of week ("Mon" to "Sun")
     *
     * Return value:
     * An integer giving the number of dates that fell on `dayOfWeek`.
     */
    public static int countDatesOnDay( SimpleDate[] dates, String dayOfWeek ) {
        int i = 0;
        for (int x = 0; x < dates.length ; x++){
            if (dayOfWeek(dates[x]).equals(dayOfWeek)){
                i++;
            }
        }
        return i;
    }


    /*
     * A method to find the most frequent day-of-week among a collection of
     * dates.
     *
     * Arguments:
     * `dates` -- an array of SimpleDate objects
     *
     * Return value:
     * If the array `dates` is empty, then this method should return the null
     * reference. Otherwise, the method should return the three-letter
     * abbreviation ("Mon", "Tue", etc.) of the day-of-week that was most
     * frequent.
     * In the case that there is a tie for the most-frequent day-of-week,
     * priority should be given to the day-of-week that comes earliest in the
     * week. (For this method, "Mon" is assumed to be the first day of the
     * week.)
     * For example, if there were a tie between Tuesday, Wednesday, and Sunday,
     * the method should return "Tue".
     */
    public static String mostFrequentDayOfWeek( SimpleDate[] dates ) {
        String [] dayNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        if (dates.length == 0){
            return null;
        }
        else{
            int currentMax = 0;
            String[] mostFreq = {""};
            for (int x = 6; x >= 0 ; x--){
                if (countDatesOnDay(dates, dayNames[x]) >= currentMax){
                    mostFreq[0] = dayNames[x];
                    currentMax = countDatesOnDay(dates, dayNames[x]);
                }
            }
            return mostFreq[0];
        }
    }


    public static String printDates(String[] dateArray){
        //Convert to String Array to put into SimpleDate
        SimpleDate[] simpleDateArray = new SimpleDate[dateArray.length];
        for (int i = 0; i < dateArray.length; i++){
            simpleDateArray[i] = new SimpleDate(dateArray[i]);
        }

        return mostFrequentDayOfWeek(simpleDateArray);
    }
}
