package com.thinkbridge.utilities;

import io.qameta.allure.Step;

// This class find current working directory
public class FindCurrentWorkingDirectory {

    // Purpose : Returns cwd
    // Author : Veena Katiyar
    @Step("Find current working directory")
    public static String getCwd(){
        String cwd = "";
        try{
            cwd = System.getProperty("user.dir");

            System.out.println("The current working directory is " + cwd);
        }catch (Exception e){
            ExceptionHandler.printMsg("getCwd",e);
        }
        return cwd;
    }

    // Purpose : Find whether the current working directory is on Linux system
    // Author : Veena Katiyar
    @Step("Find current working directory")
    public static boolean isRemoteDir(){
        boolean bFlag = false;
        try{
            String cwd = System.getProperty ("user.dir");
            StringUtils.addComment("Current working directory is > " + cwd);
            int i = cwd.indexOf("/");
            if(i >= 0){
                bFlag = true;
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("isRemoteDir",e);
        }
        return bFlag;
    }
}
