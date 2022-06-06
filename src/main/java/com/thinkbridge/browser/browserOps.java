package com.thinkbridge.browser;

import com.codeborne.selenide.WebDriverRunner;
import com.thinkbridge.utilities.DateUtils;
import com.thinkbridge.utilities.ExceptionHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

import static com.thinkbridge.utilities.ScreenShot.takeScreenShot;
import static java.lang.Thread.sleep;

// This class will be used when we want to test on multiple browsers. Rightnow , we are testing only on chrome
public class browserOps {

    // Purpose : Check whether the web page has  loaded fully or not
    // Author : Veena Katiyar
    @Step("Check whether the web page has  loaded fully or not")
    public static boolean waitForPageLoading(){
        boolean bFlag = false;
        try {
            JavascriptExecutor j = (JavascriptExecutor)WebDriverRunner.getWebDriver();
            // Wait till page loads
            do {
                String status = j.executeScript("return document.readyState").toString();
                if (status.equalsIgnoreCase("complete")) {
                    System.out.println("Page has loaded : " + DateUtils.findTimeStamp());
                    break;
                }else{
                    System.out.println("Page is loading" + DateUtils.findTimeStamp());
                }
                sleep(100);
            }while(true);
            takeScreenShot("waitForPageLoading");
        }catch (Exception e){
            ExceptionHandler.printMsg("waitForPageLoading",e);
        }
        return bFlag;
    }

    //========================================================================================

    // Purpose : Close web browser
    // Author : Veena Katiyar
    @Step("Close Web Browser")
    public static void closeBrowser(){
        WebDriverRunner.getWebDriver().close();
    }

    //========================================================================================

    // Purpose : Close web browser
    // Author : Veena Katiyar
    @Step("Quit Web Browser")
    public static void QuitBrowser(){
        WebDriverRunner.getWebDriver().quit();
    }

    //========================================================================================

    // Purpose : Return web Driver
    // Author : Veena Katiyar
    @Step("Get Web Driver")
    public static WebDriver getDriver(){
        WebDriver wb = WebDriverRunner.getWebDriver();
        return wb;
    }

    //========================================================================================

    // Purpose : Switch to Tab , parent Tab has index 0
    // Author : Veena Katiyar
    @Step("Switch to Tab : {0}")
    public static void switchToTab(int tabIndex){
        try{
            WebDriver driver = getDriver();
            ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());

            //switch to desired tab
            driver.switchTo().window(newTb.get(tabIndex));
            System.out.println("Page title of new tab: " + driver.getTitle());
            takeScreenShot("switchToTab");
        }catch (Exception e){
            ExceptionHandler.printMsg("switchToTab",e);
        }
    }

    //========================================================================================

    // Purpose : Switch to parent tab
    // Author : Veena Katiyar
    @Step("Switch to parent tab")
    public static void switchToParentTab(){
        try{
            switchToTab(0);
        }catch (Exception e){
            ExceptionHandler.printMsg("switchToParentTab",e);
        }
    }
}
