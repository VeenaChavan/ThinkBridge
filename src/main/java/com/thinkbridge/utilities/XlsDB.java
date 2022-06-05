package com.thinkbridge.utilities;

import com.codoid.products.fillo.Recordset;
import java.util.ArrayList;

// Class for managing functions for Excel files as DB
public class XlsDB{

    // Purpose : Move data from recordset to 2-D array
    // Author : Veena Katiyar
    static public String [][] recordsetToStringArray(Recordset rs){
        int recordCount = 0;
        Boolean bFlag = false;
        String strValue;
        String strResult = null;
        String[][]arrData=null;
        try{
            recordCount = rs.getCount();
            System.out.println("No. of records > "+ recordCount);
            ArrayList<String>arrTable = rs.getFieldNames();
            int colCount = arrTable.size();
            arrData = new String[recordCount][colCount];
            for(int iCount = 0;iCount<recordCount;iCount++){
                for(int colCounter=0;colCounter<colCount;colCounter++)
                    arrData[iCount][colCounter]=rs.getField(arrTable.get(colCounter));
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("recordsetToStringArray",e);
        }
        return  arrData;
    }
}
