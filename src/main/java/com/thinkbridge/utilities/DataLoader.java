package com.thinkbridge.utilities;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

// Class for loading Test data
public class DataLoader {

    // Purpose : Loads properties file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    @Step("Loads property file {0}")
    public static Properties loadPropertiesFile(String fileName) throws IOException {
        Properties props = new Properties();
        try{
            boolean result = false;
            props = new Properties();
            InputStream inputStream = FileUtils.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);
            props.load(inputStream);

            if (!props.isEmpty()) {
                for (Object propObj : props.keySet()) {
                    String prop = String.valueOf(propObj);
                    if(prop.startsWith("//")==false) {
                        if (!System.getProperties().containsKey(prop)) {
                            System.setProperty(prop, props.getProperty(prop));
                        }
                    }
                }
            }
            System.out.println("PASS : Properties file loaded successfully > " + fileName );
        }catch(Exception e){
            System.out.println("ERROR : Exception in loadPropertiesFile > " + e);
            ExceptionHandler.printMsg("loadPropertiesFile",e);
        }
        return props;
    }

    //==========================================================================

    // Purpose : Loads test case info into 2D array
    // Author : Veena Katiyar
    @Step("Loads test case info into 2D array")
    public static String[][] loadTestCaseInfo() {
        String[][] testCaseData = null;
        try {
            if (System.getProperty("config.datasource").equalsIgnoreCase("excel")) {
                testCaseData = XlsReader.loadTestCaseInfo();
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("loadTestCaseInfo",e);
        }
        return testCaseData;
    }

    //==========================================================================

    // Purpose : Loads global test data into dictionary
    // Author : Veena Katiyar
    @Step ("Loads global test data into dictionary")
    public static Dictionary<String,String> loadGlobalTestData(){
        Dictionary <String,String>testData = new Hashtable();
        try {
            if (System.getProperty("config.datasource").equalsIgnoreCase("excel")) {
                testData = XlsReader.loadGlobalTestData();
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("loadGlobalTestData",e);
        }
        return testData;
    }

    //==========================================================================

    // Purpose : Loads test case data into dictionary
    // Author : Veena Katiyar
    @Step("Loads test case data into dictionary")
    public static Dictionary <String,String>loadTestCaseData(String testName,String[][]testCaseData){
        Dictionary<String,String> testData = new Hashtable();
        try {
            if (System.getProperty("config.datasource").equalsIgnoreCase("excel")) {
                testData = XlsReader.loadTestCaseData(testName,testCaseData);
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("loadTestCaseData",e);
        }
        return testData;
    }
}
