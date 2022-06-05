package com.thinkbridge.utilities;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

// This class takes screenshot of the application at the moment
public class ScreenShot {

    private static WebDriver driver;

    // Purpose : Take Screenshot
    // Author : Veena Katiyar
    @Step("Take Screenshot : {0}")
    public static void takeScreenShot(String fileName){
        try{
            String uniqueNo = DateUtils.getTimeStamp();
            fileName = fileName.concat("_").concat(uniqueNo);
            driver = WebDriverRunner.getWebDriver();
            Allure.addAttachment(fileName ,new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        }catch (Exception e){
            ExceptionHandler.printMsg("takeScreenShot",e);
        }
    }
}
