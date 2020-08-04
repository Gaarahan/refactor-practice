package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<>();
    private static final int MAX_YEAR = 2012;
    private static final int MIN_YEAR = 2000;
    private static final int MAX_MONTH = 12;
    private static final int MIN_MONTH = 1;
    private static final int MAX_DATE = 31;
    private static final int MIN_DATE = 1;
    private static final int MAX_HOUR = 23;
    private static final int MIN_HOUR = 0;
    private static final int MAX_MINUTE = 59;
    private static final int MIN_MINUTE = 0;

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year = this.getYear();
        int month = this.getMonth();
        int date = this.getDate();
        int hour, minute;

        if (this.hasHourAndMinute()) {
            hour = this.getHour();
            minute = this.getMinute();
        } else {
            hour = 0;
            minute = 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(KNOWN_TIME_ZONES.get("UTC"));
        calendar.set(year,month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getMinute() {
        try {
            String minuteString = dateAndTimeString.substring(14, 16);
            int minute = Integer.parseInt(minuteString);
            if (minute < MIN_MINUTE || minute > MAX_MINUTE) {
                throw new IllegalArgumentException("Minute cannot be less than 0 or more than 59");
            }
            return minute;
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Minute string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Minute is not an integer");
        }
    }

    private int getHour() {
        try {
            String hourString = dateAndTimeString.substring(11, 13);
            int hour = Integer.parseInt(hourString);
            if (hour < MIN_HOUR || hour > MAX_HOUR) {
                throw new IllegalArgumentException("Hour cannot be less than 0 or more than 23");
            }
            return hour;
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Hour string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Hour is not an integer");
        }
    }

    private boolean hasHourAndMinute() {
        return this.dateAndTimeString.charAt(11) == 'Z';
    }

    private int getDate() {
        try {
            String dateString = dateAndTimeString.substring(8, 10);
            int date = Integer.parseInt(dateString);
            if (date < MIN_DATE || date > MAX_DATE) {
                throw new IllegalArgumentException("Date cannot be less than 1 or more than 31");
            }
            return date;
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Date string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Date is not an integer");
        }
    }

    private int getMonth() {
        try {
            String monthString = dateAndTimeString.substring(5, 7);
            int month = Integer.parseInt(monthString);

            if (month < MIN_MONTH || month > MAX_MONTH) {
                throw new IllegalArgumentException("Month cannot be less than 1 or more than 12");
            }

            return month;
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Month string is less than 2 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Month is not an integer");
        }

    }

    private int getYear() {
        try {
            String yearString = dateAndTimeString.substring(0, 4);
            int year = Integer.parseInt(yearString);
            if (year < MIN_YEAR || year > MAX_YEAR) {
                throw new IllegalArgumentException("Year cannot be less than 2000 or more than 2012");
            }
            return  year;
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Year string is less than 4 characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year is not an integer");
        }

    }
}
