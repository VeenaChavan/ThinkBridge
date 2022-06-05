package com.thinkbridge.utilities;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

// Class for managing String functions
public class StringUtils {

    // Purpose : Removes special characters from the String
    // Author : Veena Katiyar
    @Step("Removes special characters from the String {0}")
    public static String removeAllSpecialChars(String textString){
        String result="";
        try {
            // Set of special characters
            String regex = "[.&/\\#@$%,;:-^*()+]";
            textString = textString.replaceAll(" ", "");
            result = textString.replaceAll(regex, "");
        }catch (Exception e){
            ExceptionHandler.printMsg("removeAllSpecialChars",e);
        }
        return result;
    }

    //========================================================

    // Purpose : Add comments into Console output
    // Author : Veena Katiyar
    //@Step("Add comments into Console output")
    public static void addComment(String textString){
        String stars = "************************************************************************************************";
        try {
            System.out.println();
            System.out.println(stars);
            System.out.println(stars);
            System.out.println();

            int i = stars.length();
            int j = textString.length();
            if(j < i ){
                i = i-j;
                i = i/2;
                for (j = 0 ; j<i ; j++){
                    System.out.print(" ");
                }
                System.out.println(textString);
                for (j = 0 ; j<i ; j++){
                    System.out.print(" ");
                }
                System.out.println();
            }

            System.out.println(stars);
            System.out.println(stars);
        }catch (Exception e){
            ExceptionHandler.printMsg("addComment",e);
        }
    }

    //========================================================

    // Purpose : Get random string
    // Author : Veena Katiyar
    @Step("Get random string of length {0} use letters {1} use number {2}")
    public static String getRandomString(int textLength,boolean useLetters,boolean useNumbers){
        String result="";
        try {
                result = RandomStringUtils.random(textLength,useLetters,useNumbers);
               // System.out.println("Text String => " + result);
        }catch (Exception e){
            ExceptionHandler.printMsg("getRandomString",e);
        }
        return result;
    }
}
