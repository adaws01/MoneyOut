package model.MoneyOutPrimitives;

/**
 * Represents a date in YYYYMMDD Format, where Date is an integer.
 */

public class Date {
    private int d; //Stores date as YYYYMMDD

    //REQUIRES: d is int of exactly 8 integers, where 1 <= MM <= 12, 1 <= DD <= 31,
    //          and MMDD is consistent with the days of the Gregorian Calendar.
    //          Only CE dates are considered (these specific financial systems are a recent development)
    //EFFECTS: Constructs the Date (d)
    public Date(int d){
        this.d = d;
    }

    //EFFECTS: returns this' date in YYYYMMDD format
    public int getDate(){return d;}

    //EFFECTS: returns this' year
    public int getYear(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(0,4);
        return Integer.parseInt(subst);

    }

    //EFFECTS: returns this' month (leading 0 is dropped)
    public int getMonth(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(4, 6);
        return Integer.parseInt(subst);
    }

    //EFFECTS: returns this' day (leading 0 is dropped)
    public int getDay(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(6, 8);
        return Integer.parseInt(subst);
    }

    //EFFECTS: returns a new Date one month before this.
    //NOTE: If this.getDay() is the last day of the month, the returned date will be the last day of the previous month
    public Date getMonthAgo() {
        int year = this.getYear();
        int month = this.getMonth();
        int day = this.getDay();
        int newYear;
        int newMonth;
        if (month == 1) {
            newMonth = 12;
            newYear = year - 1;
        } else {
            newMonth = month - 1;
            newYear = year;
        }
        int newDay;
        if (day == 31) {
            if (month == 3) {
                newDay = 28;           //not dealing with leap years
            } else {
                newDay = 30;
            }
        } else {
            newDay = day;
        }
        return new Date(Integer.parseInt(newYear + ensureLeadingZero(newMonth)
                + ensureLeadingZero(newDay)));
    }

    //EFFECTS: Adds a leading zero to any integer that requires it for parsing to Date.
    //         Helper method for day and month in getMonthAgo()
    private String ensureLeadingZero(int integer) {
        if (integer < 10) {
            return ("0" + integer);
        } else {
            return String.valueOf(integer);
        }
    }

    //Setters omitted here as Dates only need to be reset within other classes with setters of their own.
}
