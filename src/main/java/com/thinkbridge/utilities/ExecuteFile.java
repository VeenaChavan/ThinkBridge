package com.thinkbridge.utilities;

import io.qameta.allure.Step;

import java.io.IOException;

// This class executes .bat files , AutoIT scripts
public class ExecuteFile {

    // Purpose : Execute bat file and exe files with paramaeters (D:\\TestAutomation\\BatFiles\\OpenEmulator.bat Emulator1)
    // Author : Veena Katiyar
    @Step("Execute file : {0}")
    public static void runFile(String fileName ) {
        try {
            StringUtils.addComment(" In runFile ");
            String cwd = System.getProperty ("user.dir");
            StringUtils.addComment("Current working directory is > " + cwd);

            Process process = null;
            fileName = cwd.concat("\\").concat(fileName);
            int i = fileName.indexOf("/");
            String command = "";
            if(i >= 0){
                fileName = fileName.replace("\\","/");
                System.out.println("File to execute > " + fileName);

                process = Runtime.getRuntime().exec("sh -c chmod +x " + fileName);
                System.out.println("Execute permission granted on  > " + fileName);
                process.waitFor();

                process = Runtime.getRuntime().exec("sh -c ./" + fileName);
                System.out.println("Executed file  > " + fileName);
                process.waitFor();
            }else {
                process = Runtime.getRuntime().exec("cmd /c start " + fileName);
                process.waitFor();
            }
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            ExceptionHandler.printMsg("runFile",e);
        }
    }
}

