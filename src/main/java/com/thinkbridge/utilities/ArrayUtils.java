package com.thinkbridge.utilities;

// Class for handling array related functions
public class ArrayUtils {

    // Purpose : deep copy source array to destination array.It preserves already existing data in destination array
    // Author : Veena Katiyar
    public static String[][]copy2DArray(String[][] sourceArray,String[][] destinationArray){
        try{
            // If source array == null, return destination array
            if (sourceArray==null){
                return destinationArray;
            }
            // Find empty slot in destination array as we don't want to overwrite over existing elements of destination array
            int iLen = destinationArray.length;
            boolean bFlag = false;
            int i,j=0;
            int allNull=0;
            for (i = 0 ;i<iLen;i++){
                allNull=0;
                for(j=0;j<destinationArray[i].length;j++){
                    if(destinationArray[i][j]==null){
                        allNull++;
                    }
                }
                if(allNull==j){
                    break;
                }
            }

            // Copy Source into destination now
            for( int rowCounter = i;rowCounter<sourceArray.length+i;rowCounter++){
                for(int colCounter=0;colCounter<destinationArray[rowCounter].length;colCounter++) {
                    destinationArray[rowCounter][colCounter]=sourceArray[rowCounter-i][colCounter];
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("copy2DArray",e);
        }
        return destinationArray;
    }
}
