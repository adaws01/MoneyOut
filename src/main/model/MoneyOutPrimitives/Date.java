package model.MoneyOutPrimitives;

//Represents a date in YYYYMMDD Format, where Date is an integer.

public class Date {
    private int d;

    //REQUIRES: d is int of exactly 8 integers, where 1 <= MM <= 12, 1 <= DD <= 31,
    //          and MMDD is consistent with the days of the Gregorian Calendar.
    //EFFECTS: Constructs the Date (d)
    public Date(int d){
        this.d = d;
    }

    public int getDate(){return d;}

    public int getYear(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(0,4);
        return Integer.parseInt(subst);

    }
    public int getMonth(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(4, 6);
        return Integer.parseInt(subst);
    }
    public int getDay(){
        int date = d;
        String s = Integer.toString(date);
        String subst = s.substring(6, 8);
        return Integer.parseInt(subst);
    }

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
        return new Date(newYear + newMonth + newDay);
    }

    //Setters omitted here as Dates only need to be reset within other classes with setters of their own.
}
