package com.thinkbridge.utilities;

import io.qameta.allure.Step;

import static com.thinkbridge.utilities.ScreenShot.takeScreenShot;

public class ExceptionHandler {
    // Purpose : Handles exception situation in {0} and takes screenshot
    // Author : Veena Katiyar
    @Step("Handles exception situation in {0} function and takes screenshot for Exception {1}")
    public static void printMsg(String funName,Exception e){
        StringUtils.addComment("EXCEPTION OCCURED");
        System.out.println("Exception in > " + funName);
        System.out.println("Cause is > " + e.getCause());
        System.out.println("Message is > " + e.getMessage());
        e.printStackTrace();
        StringUtils.addComment("EXCEPTION OCCURED");
        String fileName = NumberUtils.getuniqueNo();
        takeScreenShot(fileName);
    }

    //====================================================================================

    @Step("Handles exception situation {0} and takes screenshot")
    public static void printMsg(Exception e){
        StringUtils.addComment("EXCEPTION OCCURED");
        System.out.println("Cause is > " + e.getCause());
        System.out.println("Message is > " + e.getMessage());
        e.printStackTrace();
        StringUtils.addComment("EXCEPTION OCCURED");
        String fileName = NumberUtils.getuniqueNo();
        takeScreenShot(fileName);
    }
}
