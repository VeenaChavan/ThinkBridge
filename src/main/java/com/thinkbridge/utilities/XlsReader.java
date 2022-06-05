package com.thinkbridge.utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.qameta.allure.Step;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.thinkbridge.utilities.ArrayUtils.copy2DArray;

// class for handling reading data from Excel files and saving it in HashMap
public class XlsReader {
    String sqlQuery;
    public static Connection connection = null;
    public static Recordset recordset = null;
    public static int counter=0;
    static List<List> tableData = new ArrayList<List>();

    // Purpose : Read data from Excel
    // Author : Veena Katiyar
    @Step("Read data from Excel file {0}")
    public static Recordset readXLS(String fileName, String sqlQuery) throws FilloException {
        try{
            int i = fileName.indexOf("/");
            if(i >= 0){
                fileName = fileName.replace("\\","/");
            }

                if (FileUtils.fileExists(fileName) == true) {
                    System.out.println("File name > " + fileName);
                    Fillo fillo = new Fillo();
                    connection = fillo.getConnection(fileName);
                    recordset = connection.executeQuery(sqlQuery);
                    System.out.println("PASS : Data read from file > " + fileName);
                } else
                    System.out.println("ERROR : File does not exist > " + fileName);
            }catch(Exception e){
                ExceptionHandler.printMsg("readXLS",e);
            }
            return recordset;
    }

    //========================================================

    // Purpose : Loads test case data for testcase {0} from Excel file to Dictionary
    // Author : Veena Katiyar
    @Step("Loads test case data for testcase {0} from Excel file to Dictionary")
    public static Dictionary loadTestCaseData(String testName, String [][]testCaseData) throws FilloException {
        String testDataID = null;
        String testDataFile = null;
        String testDataSheet = null;
        Dictionary<String, String> testData = new Hashtable();
        String text1="";
        String text2="";
        try {
            for (int iCounter = 0; iCounter < testCaseData.length; iCounter++) {
                text1 = testName;
                text2 = testCaseData[iCounter][2];
                if (testCaseData[iCounter][2].equalsIgnoreCase(testName) == true) {
                    testDataID = testCaseData[iCounter][0];
                    testDataFile = testCaseData[iCounter][3];
                    testDataSheet = testCaseData[iCounter][4];
                    System.out.println("Test data ID > " + testDataID);
                    break;
                }
            }

            // Load test data , when workbook name, worksheet name and TDID are given
            if ((testDataFile != "") && (testDataSheet != "") && (testDataID.equalsIgnoreCase("NoData") == false)) {
                String cwd = FindCurrentWorkingDirectory.getCwd();
                testDataFile = cwd.concat(System.getProperty("db.testdatapath")).concat(testDataFile);

                if (FileUtils.fileExists(testDataFile) == true) {
                    try {
                        String sqlQuery = "Select Desc,Value from " + testDataSheet + " where TDID = '" + testDataID + "'";
                        recordset = XlsReader.readXLS(testDataFile, sqlQuery);
                        while (recordset.next()) {
                            testData.put(recordset.getField("Desc"), recordset.getField("Value"));
                        }
                    } catch (Exception e) {
                        ExceptionHandler.printMsg(e);
                    }
                } else {
                    System.out.println("ERROR : Test data file does not exist > " + testDataFile);
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("loadTestCaseData",e);
        }
        return testData;
    }

    //========================================================

    // Purpose : Load global test data into dictionary
    // Author : Veena Katiyar
    @Step("Loads Global Test Data")
    public static Dictionary loadGlobalTestData() throws FilloException {
        Dictionary testData = new Hashtable();
       try{
            String sqlQuery = "Select Desc,Value from GlobalData";
            String cwd = FindCurrentWorkingDirectory.getCwd();
            String fileName = cwd.concat(System.getProperty("db.testdatapath")).concat(System.getProperty("config.globaldatafile"));
            int i = fileName.indexOf("/");
            if(i >= 0){
                fileName = fileName.replace("\\","/");
            }
            if(FileUtils.fileExists(fileName) == true) {
                recordset = XlsReader.readXLS(fileName, sqlQuery);
                while (recordset.next()) {
                    testData.put(recordset.getField("Desc"), recordset.getField("Value"));
                }
                System.out.println(testData.size());
            }else{
                System.out.println("Global test data file does not exists > " + fileName);
            }
        }catch(Exception e){
            ExceptionHandler.printMsg("loadGlobalTestData",e);
        }
        return testData;
    }

    //========================================================

    // Purpose : Loads recordset into 2-D String array
    // Author : Veena Katiyar
    @Step("Loads recordset into 2-D String array")
    public static String[][] loadIntoStringArray(String[][]testCaseInfo){
        String [][]testCaseInfo1 = null;
        try{
            int row,rowCount,colCount = 0;
            System.out.println("Size of recordset > " +recordset.getCount());
            System.out.println(recordset.getFieldNames().size());
            rowCount = recordset.getCount();
            colCount = recordset.getFieldNames().size();
            int arrLength=0;
            if(testCaseInfo!=null) {
                arrLength = testCaseInfo.length;
            }
            testCaseInfo1 = new String[rowCount + arrLength][colCount];
            if(testCaseInfo!=null) {
                testCaseInfo1=copy2DArray(testCaseInfo,testCaseInfo1);
            }
            String data="";
            ArrayList<String>colNames=recordset.getFieldNames();
            while(recordset.next()){
                for(int colCounter=0;colCounter<colCount;colCounter++) {
                    data=recordset.getField(colNames.get(colCounter));
                    testCaseInfo1[arrLength][colCounter] =recordset.getField(colNames.get(colCounter));
                }
                arrLength++;
            }
        }catch(Exception e)
        {
            ExceptionHandler.printMsg("loadIntoStringArray",e);
        }
        return testCaseInfo1;
    }

    //========================================================

    // Purpose : Loads Test Case Info into 2-D String array
    // Author : Veena Katiyar
    @Step("Loads Test Case Info into 2-D String array")
    public static String[][] loadTestCaseInfo() throws FilloException {
        String sqlQuery = "";
        String cwd = FindCurrentWorkingDirectory.getCwd();
        String fileName = cwd.concat(System.getProperty("db.testdatapath")).concat(System.getProperty("config.testcasefile"));
        String[] colHeader = null;
        String colText = "";
        String[][] testCaseInfo = null;
        try {
            int i = fileName.indexOf("/");
            if(i >= 0){
                fileName = fileName.replace("\\","/");
            }

            if (FileUtils.fileExists(fileName) == true) {
                // Get names of all worksheets in the workbook
                String[] sheetNames = XlsReader.getSheetNames(fileName);
                colHeader = XlsReader.getHeaderTextWorkSheet(fileName, sheetNames[0]);
                for (i = 0; i < colHeader.length; i++)
                    colText = colText.concat(colHeader[i]).concat(",");
                colText = colText.substring(0, colText.length() - 1);

                for (int sheetCounter = 0; sheetCounter < sheetNames.length; sheetCounter++) {
                    sqlQuery = "Select " + colText + " from " + sheetNames[sheetCounter];
                    recordset = readXLS(fileName, sqlQuery);
                    testCaseInfo = loadIntoStringArray(testCaseInfo);
                }
            }else{
                System.out.println("Test Case Info File does not exist > " + fileName);
            }
        } catch (Exception e) {
            ExceptionHandler.printMsg("loadTestCaseInfo",e);
        }
        return testCaseInfo;
    }

    //========================================================

    // Purpose : Finds worksheet names of a workbook
    // Author : Veena Katiyar
    @Step("Finds worksheet names of a workbook {0}")
    public static String[]getSheetNames(String fileName){
        String[] sheetNames=null;
        try{
            //Read the given XL sheet
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            System.out.println("Number of sheets in this workbook : " + workbook.getNumberOfSheets());
            sheetNames = workbook.getSheetNames();
            for (int i = 0 ; i < sheetNames.length ; i ++ ) {
                System.out.println("Sheet Name[" + i + "] = " + sheetNames[i]);
            }
            //Close and free allocated memory
            workbook.close();
        }catch (Exception e){
            ExceptionHandler.printMsg("getSheetNames",e);
        }
        return  sheetNames;
    }

    //========================================================

    // Purpose : Gets header text of the worksheets in the workbook
    // Author : Veena Katiyar
    @Step("Gets header text of all the worksheets in the workbook {0}")
    public static String[]getHeaderTextWorkBook(String fileName){
        String [] sheetNames=null;
        String []headerText = null;
        try{
            //Read the given XL sheet
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            System.out.println();
            System.out.println("Number of sheets in this workbook "+ fileName + " > " + workbook.getNumberOfSheets());
            sheetNames = workbook.getSheetNames();
            System.out.println();
            String [][]sheetHeaders=null;
            for (int i = 0 ; i < sheetNames.length ; i ++ ) {
                headerText=getHeaderTextWorkSheet(fileName,sheetNames[i]);
                System.out.println();
            }
            //Close and free allocated memory
            workbook.close();
        }catch (Exception e){
            ExceptionHandler.printMsg("getHeaderTextWorkBook",e);
        }
        return  sheetNames;
    }

    //========================================================

    // Purpose : Gets header text of 1 worksheet
    // Author : Veena Katiyar
    @Step("Gets header text of 1 worksheet")
    public static String[] getHeaderTextWorkSheet(String fileName,String sheetName){
        String[]colText=null;
        try{
            Cell[] headerText = null;
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(sheetName);
            headerText = sheet.getRow(0);
            colText=new String[headerText.length];
            for(int p=1;p<=headerText.length;p++) {
                colText[p-1]=headerText[p-1].getContents();
                System.out.print(headerText[p-1].getContents() + " : ");
            }
            System.out.println();
        }catch(Exception e){
            ExceptionHandler.printMsg("getHeaderTextWorkSheet",e);
        }
        return colText;
    }

    //========================================================

    // Purpose : Create workbook
    // Author : Veena Katiyar
    @Step("Creates Workbook {0} with sheets {1}")
    public static void createWorkBook(String fileName,String[]sheetNames){
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
            // Create worksheets in workbook
            if (sheetNames != null) {
                for (int sheetCounter = 0; sheetCounter < sheetNames.length; sheetCounter++) {
                    workbook.createSheet(sheetNames[sheetCounter], sheetCounter);
                }
            }
            workbook.write();
            workbook.close();
        } catch (WriteException | IOException e) {
            ExceptionHandler.printMsg("createWorkBook",e);
        }
    }

    //========================================================

    // Purpose : Cleanup
    // Author : Veena Katiyar
    @Step("Clean up DB objects")
    public static void cleanUp() {
        try {
            if (recordset != null)
                recordset.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            ExceptionHandler.printMsg("cleanUp",e);
        }
    }
}
