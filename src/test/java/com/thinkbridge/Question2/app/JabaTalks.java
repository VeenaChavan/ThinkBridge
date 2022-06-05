package com.thinkbridge.Question2.app;

import com.codeborne.selenide.Configuration;
import com.thinkbridge.WebKeywords;
import com.thinkbridge.browser.browserOps;
import com.thinkbridge.utilities.ExceptionHandler;
import com.thinkbridge.utilities.ScreenShot;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Selenide.open;
import static com.thinkbridge.Question2.common.BaseTest.getTestData;
import static com.thinkbridge.utilities.ScreenShot.takeScreenShot;
import static java.lang.Thread.sleep;

public class JabaTalks {
    @Step("Open Jaba Talks Website")
    public static void openJabaTalks(){
        try {
            Configuration.baseUrl = getTestData("TD_AppUrl");
            open(Configuration.baseUrl);
            sleep(500);
            ScreenShot.takeScreenShot("openJabaTalks");
        } catch (Exception e){
            ExceptionHandler.printMsg("openJabaTalks",e);
        }
    }

    //=================================================================================

    @Step("Register user : {0}")
    public static void registerUser(String userName,String organizationName,String emailId, boolean acceptTerms){
        try{
            // Set full name
            setFullName(userName);

            // Set organization name
            setOrganizationName(organizationName);

            // Set email id
            setEmail(emailId);

            // Agree to Tesrms & Conditions
            agreeToTermsAndConditions(acceptTerms);

            // Click on Get Started button
            boolean bFlag = isGetStartedButtonDisabled();
            if(bFlag == false){
                clickGetStartedButton();
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("registerUser",e);
        }
    }

    //=================================================================================

    @Step("Select language as {0}")
    public static void selectLanguage(String language){
        try {
            WebKeywords.clickObject("xpath", ".//div[@name='language']");
            sleep(100);
            WebKeywords.clickObject("xpath", ".//div[@role='option']/a/div[contains(.,'" + language + "')]");
            sleep(100);
        }catch (Exception e){
            ExceptionHandler.printMsg("selectLanguage",e);
        }
    }

    //=================================================================================

    @Step("Enter Full Name as {0}")
    public static void setFullName(String fullName){
        try {
            WebKeywords.setText("xpath", ".//input[@name='name']",fullName);
            sleep(100);
        }catch (Exception e){
            ExceptionHandler.printMsg("setFullName",e);
        }
    }

    //=================================================================================

    @Step("Enter Organization Name as {0}")
    public static void setOrganizationName(String organizationName){
        try {
            WebKeywords.setText("xpath", ".//input[@name='orgName']",organizationName);
            sleep(100);
        }catch (Exception e){
            ExceptionHandler.printMsg("setOrganizationName",e);
        }
    }

    //=================================================================================

    @Step("Enter Email as {0}")
    public static void setEmail(String emailId){
        try {
            WebKeywords.setText("xpath", ".//input[@name='email']",emailId);
            sleep(100);
        }catch (Exception e){
            ExceptionHandler.printMsg("setEmail",e);
        }
    }

    //=================================================================================

    @Step("Agree to Terms & Conditions as {0}")
    public static void agreeToTermsAndConditions(boolean agreeToTermsConditions){
        try {
            if(agreeToTermsConditions == true) {
                WebKeywords.clickObject("xpath", ".//label[@class='ui-checkbox']/span");
                sleep(100);
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("agreeToTermsAndConditions",e);
        }
    }

    //=================================================================================

    @Step("Click on Get Started button")
    public static void clickGetStartedButton(){
        try {
            WebKeywords.clickObject("xpath", ".//button[@type='submit']");
            sleep(500);
        }catch (Exception e){
            ExceptionHandler.printMsg("clickGetStartedButton",e);
        }
    }

    //==========================================================================

    @Step("Verify account")
    public static void verifyAccount(){
        try{
            browserOps.switchToTab(1);
            WebKeywords.waitTillElementIsClickable("xpath",".//h2[contains(.,'Welcome back')]");
            WebKeywords.objectExists("xpath",".//h2[contains(.,'Welcome back')]");
            takeScreenShot("verifyAccount");
        }catch (Exception e){
            ExceptionHandler.printMsg("verifyAccount",e);
        }
    }

    //==========================================================================

    @Step("Verify Registration Message is displayed as A welcome email has been sent. Please check your email.")
    public static void verifyRegistrationMessage(){
        try{
            boolean bFlag = WebKeywords.waitTillElementIsClickable("xpath",".//span[contains(.,'A welcome email has been sent. Please check your email.')]");
            assertTrue(bFlag == true);
            takeScreenShot("verifyRegistrationMessage");
        }catch (Exception e){
            ExceptionHandler.printMsg("verifyRegistrationMessage",e);
        }
    }

    //==========================================================================

    @Step("Verify that the error message A user already has registered as an admin for this company. A notification has been sent to the admin for approval is displayed")
    public static void verifyAlreadyRegisteredUserMsg(){
        try{
            boolean bFlag = WebKeywords.waitTillElementIsClickable("xpath",".//span[contains(.,' User with this Email already exists. Please login or reset your password')]");
            assertTrue(bFlag == true);
            takeScreenShot("verifyAlreadyRegisteredUserMsg");
        }catch (Exception e){
            ExceptionHandler.printMsg("verifyAlreadyRegisteredUserMsg",e);
        }
    }

    //==========================================================================

    @Step("Verify that Getting started button is disabled")
    public static boolean verifyGetStartedButtonDisabled(){
        boolean bFlag = false;
        try{
            bFlag = WebKeywords.objectDisplayed("xpath",".//button[@disabled='disabled' and contains(.,'Get Started')]");
            assertTrue(bFlag == true);
            takeScreenShot("verifyGetStartedButtonDisabled");
        }catch (Exception e){
            ExceptionHandler.printMsg("verifyGetStartedButtonDisabled",e);
        }
        return !bFlag;
    }

    //==========================================================================

    @Step("Check whether the Get started button is disabled or not")
    public static boolean isGetStartedButtonDisabled(){
        boolean bFlag = false;
        try{
            bFlag = WebKeywords.objectDisplayed("xpath",".//button[@disabled='disabled' and contains(.,'Get Started')]");
            takeScreenShot("isGetStartedButtonDisabled");
        }catch (Exception e){
            ExceptionHandler.printMsg("isGetStartedButtonDisabled",e);
        }
        return bFlag;
    }

    //==========================================================================

    @Step("Verify that Get Started button is enabled")
    public static void verifyGetStartedButtonEnabled(){
        try{
            boolean bFlag = WebKeywords.objectDisplayed("xpath",".//button[@disabled='disabled' and contains(.,'Get Started')]");
            assertTrue(bFlag == false);
            takeScreenShot("verifyGetStartedButtonEnabled");
        }catch (Exception e){
            ExceptionHandler.printMsg("verifyGetStartedButtonEnabled",e);
        }
    }
}
