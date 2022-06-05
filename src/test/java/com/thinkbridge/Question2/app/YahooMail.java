package com.thinkbridge.Question2.app;

import com.thinkbridge.WebKeywords;
import com.thinkbridge.utilities.ExceptionHandler;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.thinkbridge.utilities.ScreenShot.takeScreenShot;

public class YahooMail {

    @Step("Open Yahoo mail for {0}")
    public static void openYahooMail(String username, String password){
        try{
            // Open Yahoo mail
            open("https://in.mail.yahoo.com/");
            sleep(500);
            boolean bFlag = WebKeywords.objectDisplayed("xpath",".//a[contains(.,'Sign in')]");
            if(bFlag == true) {
                WebKeywords.clickObject("xpath", ".//a[contains(.,'Sign in')]");
            }

            // Set Username
            WebKeywords.setText("name","username",username);
            WebKeywords.clickObject("value","Next");

            // Set Password
            WebKeywords.setText("name","password",password);
            WebKeywords.clickObject("value","Next");
            takeScreenShot("openYahooMail");
        }catch (Exception e){
            ExceptionHandler.printMsg("openYahooMail",e);
        }
    }

    //==========================================================================

    @Step("Delete all Emails")
    public static void deleteAllMails(){
        try{
            // Close any open notification message
            boolean bFlag = WebKeywords.objectDisplayed("xpath",".//span[@class='D_F ab_C gl_C W_6D6F']/svg");
            if(bFlag == true){
                WebKeywords.clickObject("xpath",".//span[@class='D_F ab_C gl_C W_6D6F']/svg");
            }

            // Click on Inbox
            WebKeywords.clickObject("xpath",".//div[@class='folder-list p_R k_w W_6D6F U_3mS2U']/ul/li/div/a/span[contains(.,'Inbox')]");

            // Click on Select all checkbox
            WebKeywords.clickObject("xpath",".//span[@class='D_F']/button");
            sleep(100);

            // Click on Delete icon
            WebKeywords.clickObject("xpath",".//span[@class='D_F ab_C gl_C W_6D6F']/span/span[contains(.,'Delete')]");
            sleep(500);
            takeScreenShot("deleteAllMails");
        }catch (Exception e){
            ExceptionHandler.printMsg("deleteAllMails",e);
        }
    }

    //==========================================================================

    @Step("Confirm account for {0}")
    public static void confirmAccount(){
        try{
            // Click on email in inbox
            WebKeywords.clickObject("xpath",".//div[@data-test-id='content-below-tabs']/div[2]/div/div/div/div/div[2]/div/div/div/div/div/ul/li[3]");

            // Click on Confirm account button
            WebKeywords.clickObject("xpath",".//a[contains(.,'Confirm your account')]");
            takeScreenShot("confirmAccount");
        }catch (Exception e){
            ExceptionHandler.printMsg("confirmAccount",e);
        }
    }
}
