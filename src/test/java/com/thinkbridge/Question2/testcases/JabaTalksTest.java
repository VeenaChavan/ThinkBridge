package com.thinkbridge.Question2.testcases;


import com.thinkbridge.Question2.common.BaseTest;
import com.thinkbridge.utilities.ExceptionHandler;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.thinkbridge.Question2.app.JabaTalks.*;
import static com.thinkbridge.Question2.app.YahooMail.confirmAccount;
import static com.thinkbridge.Question2.app.YahooMail.openYahooMail;

@Epic("ThinkBridge")
@Feature("Jaba Talks")
@Severity(SeverityLevel.NORMAL)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("JabaTalks")
@DisplayName("Jaba Talks")

public class JabaTalksTest extends BaseTest {

    @Test
    @Order(1)
    @Story("Jaba Talks")
    @DisplayName("TC1 : Verify languages – Dutch , English")
    @Description("Open the website.Select Dutch language.Select English language.")
    // JT_TC_001
    public void verifyLanguages_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Select languages to check that language present in the dropdown
            String[] languages = getTestData("TD_Languages").split(":");
            for (int LoopCounter = 0; LoopCounter < languages.length; LoopCounter++) {
                selectLanguage(languages[LoopCounter]);
            }
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(2)
    @Story("Jaba Talks")
    @DisplayName("TC2 : Register user")
    @Description("Open the website.Enter Full Name.Enter Organization Name.Enter Email.Click on I agree to terms & conditions.Click on Get Started button.")
    // JT_TC_002
    public void registerUser_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser(getTestData("TD_UserName"),getTestData("TD_OrganizationName"),getTestData("TD_Email"),true);

            // Verify Registration message
            verifyRegistrationMessage();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(3)
    @Story("Jaba Talks")
    @DisplayName("TC3 : Confirm Activation")
    @Description("Open the website.Register the user.Check email and click on Confirm activation button.")
    public void confirmActivation_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser(getTestData("TD_UserName"),getTestData("TD_OrganizationName"),getTestData("TD_Email"),true);

            // Open Yahoo mail
            openYahooMail(getTestData("TD_Email"),getTestData("TD_Password"));

            // Confirm account
            confirmAccount();

            // Verify account has been confirmed
            verifyAccount();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(4)
    @Story("Jaba Talks")
    @DisplayName("TC4 : Get Started Button – Disabled")
    @Description("Open the website.Do not enter value in any field.Do not check the I agree to terms & conditions checkbox.")
    public void getStartedDisabled1_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser("","","",false);

            // Verify that "Get Started" button is disabled
            verifyGetStartedButtonDisabled();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(5)
    @Story("Jaba Talks")
    @DisplayName("TC5 : Get Started Button – Disabled")
    @Description("Open the website.Enter the values for Email only.Do not check the I agree to terms & conditions checkbox.")
    public void getStartedDisabled2_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser("","",getTestData("TD_Email"),false);

            // Verify that "Get Started" button is disabled
            verifyGetStartedButtonDisabled();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(6)
    @Story("Jaba Talks")
    @DisplayName("TC6 : Get Started Button – Disabled")
    @Description("Open the website.Enter the values as per test data.Check the I agree to terms & conditions checkbox.")
    public void getStartedDisabled3_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            String email = getTestData("TD_Email");
            email = email.substring(0,email.indexOf("@"));

            // Register user
            registerUser(getTestData("TD_UserName"),getTestData("TD_OrganizationName"),email,true);

            // Verify that "Get Started" button is disabled
            verifyGetStartedButtonDisabled();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(7)
    @Story("Jaba Talks")
    @DisplayName("TC7 : Get Started Button – Enabled")
    @Description("Open the website.Enter the values as per test data.Check the I agree to terms & conditions checkbox.Click on Get Started button.")
    public void getStartedEnabled_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser(getTestData("TD_UserName"),getTestData("TD_OrganizationName"),getTestData("TD_Email"),true);

            // Verify registration message
            verifyRegistrationMessage();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }

    //==================================================================================

    @Test
    @Order(8)
    @Story("Jaba Talks")
    @DisplayName("TC8 : Register the same email id twice")
    @Description("User with email id automation.userpro@yahoo.com is already activated. This step we need not to automate.Register another user with same email id.Click on I agree to terms & conditions.Click on Get Started button.")
    public void registerAgain_Test() {
        try {
            // Open Jaba Talks Website
            openJabaTalks();

            // Register user
            registerUser(getTestData("TD_UserName"),getTestData("TD_OrganizationName"),getTestData("TD_Email"),true);

            // Verify registration message
            verifyAlreadyRegisteredUserMsg();
        } catch (Exception e) {
            ExceptionHandler.printMsg(e);
        }
    }
}
