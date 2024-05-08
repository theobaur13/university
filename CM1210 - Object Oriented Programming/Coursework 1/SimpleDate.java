/*
 * A simple class to represent a calendar date.
 * Uses a naive representation. This class does not verify that the month and
 * day values are valid.
 *
 * The month is represented by an integer between 1 (January) to 12 (December).
 * The day is represented by an integer, with 1 indicating the first day of
 * the month.
 *
 * This class should NOT be modified for Question 1 of the assessment.
 */
public class SimpleDate {
    private int year;
    private int month;
    private int day;

    public SimpleDate( int year, int month, int day ) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    public SimpleDate(String args){
        //Verbose (7th March 2022)
        if (args.length() > 10){
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            String[] parts = args.replaceAll("\"", "").split("\\s+");

            int YYYY = Integer.parseInt(parts[2]);
            int MM = -1;        //Placeholder

            for (int i = 0; i < months.length; i++){
                if (parts[1].equals(months[i])){
                    MM = (i+1);
                }
            }
            int DD = Integer.parseInt(parts[0].replaceAll("st|nd|rd|th", ""));
            this.year = YYYY;
            this.month = MM;
            this.day = DD;
            //new SimpleDate(YYYY,MM,DD);    
        }

        else if (args.length() == 10){
            String[] parts = args.replaceAll("\"", "").split("[/-]");
            int YYYY = Integer.parseInt(parts[2]);
            int MM = Integer.parseInt(parts[1]);
            int DD = Integer.parseInt(parts[0]);
            //new SimpleDate(YYYY,MM,DD);
            this.year = YYYY;
            this.month = MM;
            this.day = DD;
        }
        
        else{
            System.out.println("Invalid date input");
            System.out.println(args.length());
            //return -1;
        }           
    }






}
