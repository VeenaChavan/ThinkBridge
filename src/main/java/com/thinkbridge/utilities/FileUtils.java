package com.thinkbridge.utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Class for managing file functions
public class FileUtils {

    // Purpose : Create file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    public static boolean createFile(String fileName)
    {
        // Placeholder
        boolean result = false;
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                result=true;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            ExceptionHandler.printMsg("createFile",e);
        }
        return result;
    }

    //==========================================================================

    // Purpose : Append text in file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    public static boolean appendTextInTextFile(String fileName,String strText)
    {
        boolean result = false;
        try {

            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
            new FileWriter(fileName, true));

            // Writing on output stream
            out.write(strText);
            // Closing the connection
            out.close();
        }catch (IOException e) {
            ExceptionHandler.printMsg("appendTextInTextFile",e);
        }
        return result;
    }

    //==========================================================================

    // Purpose : Find whether file exists or not, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    @Step("Verify file {0} exists")
    public static boolean fileExists(String fileName)
    {
        String cwd = FindCurrentWorkingDirectory.getCwd();
        int i = cwd.indexOf("/");
        if(i >= 0){
            fileName = fileName.replace("\\","/");
        }
        boolean result = false;
        try
        {
            File file = new File(fileName);
            if (file.exists()==true)
                result = true;
        }catch (Exception e){
            ExceptionHandler.printMsg("fileExists",e);
        }
        return  result;
    }

    //==========================================================================

    // Purpose : Find whether file exists of specific type for ex. csv
    // Author : Veena Katiyar
    @Step("Verify file type {0} exists")
    public static String fileTypeExists(String directory, String fileType)
    {
        String fileName = "";
        try
        {
            int i = directory.indexOf("/");
            if(i >= 0){
                directory = directory.replace("\\","/");
            }
            File folder = new File(directory);
            for (File file : folder.listFiles()) {
                if (file.getName().endsWith(fileType)) {
                    fileName = file.getName();
                    System.out.println("File of type : " + fileType + " exists in folder " + directory + " > " + file.getName());
                    break;
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("fileTypeExists",e);
        }
        return  fileName;
    }

    //==========================================================================
    // Purpose : Deletes the file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    @Step("Delete file : {0}")
    public static boolean deleteFile(String fileName)
    {
        boolean result = false;
        try
        {
            String cwd = FindCurrentWorkingDirectory.getCwd();
            int i = cwd.indexOf("/");
            if(i >= 0){
                fileName = fileName.replace("\\","/");

            }
            File file = new File(fileName);
            if (file.exists()==true){
                // Delete file
                result=file.delete();
                // Verify that file has been deleted
                if(file.exists()==true){
                    System.out.println("ERROR : File can not be deleted > " + fileName);
                }else {
                    System.out.println("File deleted successfully > " + fileName);
                }
            }else{
                System.out.println("File not found for deletion > " + fileName);
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("deleteFile",e);
        }
        return  result;
    }

    //==========================================================================

    // Purpose : Returns list of files present in filePath folder of specific type
    // Author : Veena Katiyar
    @Step("Find all the files of type {1} present in folder {0}")
    public static String[]getFileNames(String filePath,String fileType) {
        String[] sheetNames = null;
        String[] filesList=null;
        try {
            File directoryPath = new File(filePath);
            FilenameFilter textFilefilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.endsWith(fileType.toLowerCase())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            //List of all the matching files
            filesList= directoryPath.list(textFilefilter);
            System.out.println("List of the " + fileType + " files in the specified directory:");
            for (String fileName : filesList) {
                System.out.println(fileName);
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("getFileNames",e);
        }
        return  filesList;
    }

    //==========================================================================

    // Purpose : Kills the process
    // Author : Veena Katiyar
    @Step("Kill process : {0}")
    public static void killProcess(String processName)
    {
        try {
            Runtime.getRuntime().exec("taskkill /f /im " + processName);
        } catch (Exception e) {
            ExceptionHandler.printMsg("killProcess",e);
        }
    }

    //==========================================================================

    // Purpose : Delete all files of same type
    // Author : Veena Katiyar
     @Step("Delete files of type {1} in folder {0}")
    public static void deleteFiles(String folderName,String fileType) {
        File folder = new File(folderName);
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(fileType)) {
                file.delete();
            }
        }
    }

    //==========================================================================

    // Purpose : Deletes the file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    @Step("Delete file having name as {1}")
    public static boolean deleteFileNames(String filePath,String fileNames)
    {
        boolean result = false;
        try
        {
            String[] files = getFileNames(filePath,fileNames);
            for (int loopCounter = 0; loopCounter<files.length;loopCounter++){
                String fileName = filePath.concat("\\").concat(files[loopCounter]);
                if(fileName.indexOf(fileNames)==0) {
                    File file = new File(fileName);
                    file.delete();
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("deleteFileNames",e);
        }
        return  result;
    }

    //==========================================================================

    // Purpose : Copy file1 as file2
    // Author : Veena Katiyar
    @Step("Copies file {0} as {1}")
    public static boolean copyFile(String fileName,String newFileName)
    {
        boolean result = false;
        try
        {
        // Place holder
        }catch (Exception e){
            ExceptionHandler.printMsg("deleteFileNames",e);
        }
        return  result;
    }

    public static void listFilesUsingDirectoryStream_Usage(String [] l) throws IOException {
        listFilesUsingDirectoryStream("C\\Users\\Veena Katiyar\\Downloads");

    }

    public static  Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(path.getFileName()
                            .toString());
                }
            }
        }
        return fileList;
    }

    //==========================================================================

    // Purpose : Create file, give absolute path.ex. c:\temp\result.txt
    // Author : Veena Katiyar
    public static ChromeOptions setDownloadFolder()
    {
        ChromeOptions options = null;
        try {
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "SF_Downloads");
            options = new ChromeOptions();
            options.setExperimentalOption("prefs",prefs);
        }catch (Exception e){
            ExceptionHandler.printMsg("setDownloadFolder",e);
        }
        return options;
    }
}

