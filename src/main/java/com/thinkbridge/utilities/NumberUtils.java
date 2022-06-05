package com.thinkbridge.utilities;

import io.qameta.allure.Step;
import java.util.Random;

import static com.thinkbridge.utilities.StringUtils.getRandomString;

// class for handling functions related to numbers
public class NumberUtils {

    static Random randomNo;

    // Purpose : Get random no.
    // Author : Veena Katiyar
    @Step("Generate random no.")
    public static int generateRandomNo()
    {
        int randonNumber = 0;
        try {
            randomNo = new Random();
            randonNumber = Math.abs(randomNo.nextInt());
        }catch (Exception e){
            ExceptionHandler.printMsg("generateRandomNo",e);
        }
        return randonNumber;
    }

    //==========================================================================

    // Purpose : Get random no. in range
    // Author : Veena Katiyar
    @Step("Get random no. in range {0} to {1}")
    public static int getRandomNoInRange(int min,int max)
    {
        int randomNumber=0;
        try {
            randomNo = new Random();
            randomNumber = randomNo.nextInt(max - min) + min;
            StringUtils.addComment("getRandomNoInRange > Random no. =  " + randomNumber);
        }catch (Exception e){
            ExceptionHandler.printMsg("getRandomNoInRange",e);
        }
        return(randomNumber);
    }

    //==========================================================================

    // Purpose : Get unique no.
    // Author : Veena Katiyar
    @Step("Get unique no.")
    public static String getuniqueNo()
    {
        String text = getRandomString(10,true,true);
        return text;
    }
}

