package com.thinkbridge.Question2.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codoid.products.exception.FilloException;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.thinkbridge.utilities.DataLoader;
import com.thinkbridge.utilities.ExceptionHandler;
import com.thinkbridge.utilities.StringUtils;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.thinkbridge.utilities.DataLoader.loadPropertiesFile;
import static com.thinkbridge.utilities.FileUtils.deleteFile;
import static com.thinkbridge.utilities.ScreenShot.takeScreenShot;
/**
 * BaseTest class
 * <br/>
 *
 * @author Veena Katiyar
 */

// Do not comment below statement, will give illegalStateException
@ExtendWith(SoftAssertsExtension.class)
public abstract class BaseTest {
    public static Dictionary <String,String>testData = new Hashtable();
    public static String[][] testCaseData = null;

    // Names of properties files
    private final static String testConfigProperties = "testconfig.properties";
    private final static String allureProperties = "allure.properties";

    @BeforeAll
    @Step("Test suite setup - Load suite level test data")
    public void setupClass() throws IOException {
        try {
            String cwd = System.getProperty ("user.dir");
            StringUtils.addComment("Current working directory is > " + cwd);

            // Delete environment.xml
            deleteFile(cwd.concat("\\build\\allure-results\\environment.xml"));
            Configuration.timeout = 6000;
            Configuration.assertionMode = SOFT;

            // Start browser in maximized mode
            Configuration.startMaximized=true;

            // Save screenshot, when test fails
            Configuration.screenshots=true;

            // Do not save page source when test fails
            Configuration.savePageSource=false;

            // Keep browser open after execution
            Configuration.holdBrowserOpen = false;

            Configuration.browser = "chrome";
            Configuration.reopenBrowserOnFail = true; // Do not change this

            // Add AllureSelenide listener
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));

            // Load testconfig.properties file in resources
            loadPropertiesFile(testConfigProperties);

            // Load allure.properties file in resources
            loadPropertiesFile(allureProperties);

            // Load testcase info
            testCaseData=DataLoader.loadTestCaseInfo();
        }catch (Exception e)
        {
            ExceptionHandler.printMsg(e);
        }
    }

    //===================================================================================================

    @BeforeEach
    @Step("Test Case - Load test case specific test data")
    public void setupTest(TestInfo testInfo) throws FilloException  {
        try {
            // Load test case data
            String testName = testInfo.getTestMethod().get().getName();
            testData = DataLoader.loadTestCaseData(testName,testCaseData);
        }catch (Exception e){
            ExceptionHandler.printMsg(e);
        }
    }

    //===================================================================================================

    @AfterEach
    public void teardownTest(TestInfo testInfo) {
        try {
                String testName = testInfo.getTestMethod().get().getName().concat(".png");
                takeScreenShot(testName);
        }catch (Exception e){

            ExceptionHandler.printMsg(e);
        }
    }

    //===================================================================================================

    @AfterAll
    @Step("Test Suite Cleanup - Suite level cleanup")
    static void cleanupClass() {
        try {
                /*
                 * Generate environment properties to Allure report
                 * */
                ImmutableMap.Builder<String, String> environmentBuilder = ImmutableMap.builder();
                /*
                 * From selenide.properties
                 * */
                System.getProperties().forEach((key, val) -> {
                    if (key.toString().startsWith("selenide.")) {
                        environmentBuilder.put(key.toString(), val.toString());
                    }
                });
                /*
                 * From allure.properties
                 * */
                System.getProperties().forEach((key, val) -> {
                    if (key.toString().startsWith("allure.")) {
                        environmentBuilder.put(key.toString(), val.toString());
                    }
                });
                AllureEnvironmentWriter.allureEnvironmentWriter(
                        environmentBuilder.build(),
                        System.getProperty("allure.results.directory") + "/"
                );

                SelenideLogger.removeListener("AllureSelenide");
        }catch (Exception e){
            ExceptionHandler.printMsg(e);
        }
    }

    //===================================================================================================

    // Purpose : Function to retrieve test data from dictionary
    // Author : Veena Katiyar
    public static String getTestData(String key){
        String value="";
        try {
                value = testData.get(key);
            } catch (Exception e){
            ExceptionHandler.printMsg(e);
        }
        return value;
    }

    //===================================================================================================

    // Purpose : Function to set test data in dictionary
    // Author : Veena Katiyar
    public static void setTestData(String key,String value){
        try{
        testData.put(key,value);
        }catch (Exception e){
            ExceptionHandler.printMsg(e);
        }
    }
}
