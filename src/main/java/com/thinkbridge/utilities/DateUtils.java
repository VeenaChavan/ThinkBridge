package com.thinkbridge.utilities;

import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// class for managing date functions
public class DateUtils {

    // Purpose : Finds today's date in required format
    // Author : Veena Katiyar
    @Step("Finds today's date in required format {0}")
    public static String dateToday(String dateFormat)
    {
        String datetoday="";
        try{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            datetoday = sdf.format(cal.getTime());
        }catch(Exception e){
            ExceptionHandler.printMsg("dateToday",e);
        }
        return datetoday;
    }

    //==========================================================================

    // Purpose : Add/subtract no. of days from given date
    // Author : Veena Katiyar
    public static String addDays(String date, int numberOfDays,String dateFormat)
    {
        String newDate="";
        try{

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            cal.add(Calendar.DATE, numberOfDays);  // number of days to add or subtract
            newDate = sdf.format(cal.getTime());
        }catch(Exception e){
            ExceptionHandler.printMsg("addDays",e);
        }
        return newDate;
    }

    //==========================================================================

    // Purpose : Get day of month
    // Author : Veena Katiyar
    public static String getDayOfMonth(){
        String day = "";
        try {
            Calendar cal = Calendar.getInstance();
            int var = cal.get(Calendar.DAY_OF_MONTH) ;
            day =  String.valueOf(var);
        }catch (Exception e){
            ExceptionHandler.printMsg("getDayOfMonth",e);
        }
        return day;
    }

    //==========================================================================

    // Purpose : Get current month
    // Author : Veena Katiyar
    public static String getMonth(){
        String month = "";
        try {
            Calendar cal = Calendar.getInstance();
            int var = cal.get(Calendar.MONTH) + 1;
            month =  String.valueOf(var);
        }catch (Exception e){
            ExceptionHandler.printMsg("getMonth",e);
        }
        return month;
    }

    //==========================================================================

    // Purpose : Get current year
    // Author : Veena Katiyar
    public static String getYear(){
        String year = "";
        try{
            Calendar cal = Calendar.getInstance();
            int var = cal.get(Calendar.YEAR);
            year = String.valueOf(var);
        }catch (Exception e){
            ExceptionHandler.printMsg("getYear",e);
        }
        return year;
    }

    //==========================================================================

    // Purpose : Get TimeStamp
    // Author : Veena Katiyar
    @Step("Get timestamp")
    public static String getTimeStamp()
    {
        String uniqueNo = null;
        try{
            uniqueNo =  DateUtils.dateToday("dd.MM.yyyy hh.mm.ss");
            uniqueNo = StringUtils.removeAllSpecialChars(uniqueNo);
        }catch(Exception e){
            ExceptionHandler.printMsg("getTimeStamp",e);
        }
        return uniqueNo;
    }

    //==========================================================================

    // Purpose : Get TimeStamp
    // Author : Veena Katiyar
    @Step("Get timestamp")
    public static String findTimeStamp()
    {
        String uniqueNo = null;
        try{
            SimpleDateFormat SDFormat
                    = new SimpleDateFormat();

            // Initializing the calendar Object
            Calendar cal = Calendar.getInstance();

            // Using format() method for conversion
            uniqueNo = SDFormat.format(cal.getTime());
        }catch(Exception e){
            ExceptionHandler.printMsg("findTimeStamp",e);
        }
        return uniqueNo;
    }

    public static void main1(String[]a)
    {
        String sDate = findTimeStamp();

        System.out.println(sDate);
    }
}
