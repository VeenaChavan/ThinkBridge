package com.thinkbridge;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.thinkbridge.browser.browserOps;
import com.thinkbridge.utilities.DateUtils;
import com.thinkbridge.utilities.ExceptionHandler;
import com.thinkbridge.utilities.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebKeywords {

    public static  WebDriver driver;

    public WebKeywords(){
        this.driver = WebDriverRunner.getWebDriver();
    }

    // Purpose : Find SelenideElement
    // Author : Veena Katiyar
    public static SelenideElement getObject(String property, String value)
    {
        // Wait for page loading
        browserOps.waitForPageLoading();
        sleep(200);

        SelenideElement objObject = null;
        try{
            if(property.compareToIgnoreCase("id")==0) {
                objObject = $(byId(value));
            } else if(property.compareToIgnoreCase("xpath")==0) {
                objObject = $(byXpath(value));
            }else if(property.compareToIgnoreCase("cssSelector")==0) {
                objObject = $(byCssSelector(value));
            } else if(property.compareToIgnoreCase("partialLinkText")==0) {
                objObject = $(byPartialLinkText(value));
            } else if(property.compareToIgnoreCase("text")==0) { // matches exact text
                objObject = $(byText(value));
            }else if(property.compareToIgnoreCase("withText")==0) { // matches contained text
                objObject = $(withText(value));
            }else if(property.compareToIgnoreCase("name")==0) {
                objObject = $(byName(value));
            }else if(property.compareToIgnoreCase("title")==0) {
                objObject = $(byTitle(value));
            }else if(property.compareToIgnoreCase("value")==0){
                objObject = $(byValue(value));
            }else if(property.compareToIgnoreCase("class")==0){
                objObject = $(byClassName(value));
            }else if(property.compareToIgnoreCase("linkText")==0){
                objObject = $(byLinkText(value));
            }else if(property.compareToIgnoreCase("type")==0){
                objObject = $(byAttribute("type",value));
            }else if(property.compareToIgnoreCase("tagname")==0){
                objObject = $(By.tagName(value));
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("getObject",e);
        }
        return objObject;
    }

    //============================================================================

    // Purpose : Click on Object
    // Author : Veena Katiyar
    public static boolean clickObject(String objectProperty,String propValue){
        boolean bFlag = false;
        try{
            SelenideElement obj = null;
            int i=0;
            while(bFlag==false) {
                obj = getObject(objectProperty, propValue);
                obj.click();
                StringUtils.addComment("Clicked > " + propValue + " : " + DateUtils.findTimeStamp());
                bFlag = true;
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("clickObject",e);
        }
        return bFlag;
    }

    //============================================================================

    // Purpose : Sets text into textbox
    // Author : Veena Katiyar
    public static boolean setText(String property, String value,String text){
        boolean bFlag1 = false;
        try{
            WebKeywords.waitTillElementIsClickable(property,value);
            boolean bFlag = false;
            String objText = "";
            SelenideElement we = getObject(property,value);
           // we.hover();
            while (bFlag==false) {
                we.setValue(text);
                bFlag1 = true;
                objText = we.getValue();
                objText = objText.replace("<div>","");
                objText = objText.replace("</div>","");
                if (objText.equalsIgnoreCase(text) == true) {
                    bFlag = true;
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("setText",e);
        }
        return bFlag1;
    }

    //============================================================================

    // Purpose : Get Text of option in select by option value
    // Author : Veena Katiyar
    public static  boolean waitTillElementIsClickable(String property, String propValue){
        WebElement we = null;
        boolean bFlag = false;
        String message= "";
        try{
            int iCount = 0;
            while((bFlag == false)&&(iCount < 20) ){
                bFlag = WebKeywords.objectDisplayed(property,propValue);
                if(bFlag == true){
                    //message = WebKeywords.getText(property,propValue);
                    break;
                }
                sleep(30);
                iCount ++;
                System.out.println("iCount = > " + iCount);
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("waitTillElementIsClickable",e);
        }
        return bFlag;
    }

    //============================================================================

    // Purpose : Verify object is displayed or not, it does not have assertion in it
    // Author : Veena Katiyar
    public static boolean objectDisplayed(String objectProperty,String propValue){
        boolean bFlag = false;
        try{
            Thread.sleep(5000);
            SelenideElement obj = (SelenideElement) getObject(objectProperty, propValue);
            bFlag=obj.exists();
            if(bFlag==true){
                bFlag = obj.isDisplayed();
                if(bFlag == false){
                    obj.scrollIntoView(true);
                    //bFlag = true;
                }
            }
        }catch (Exception e){
            ExceptionHandler.printMsg("objectDisplayed",e);
        }
        return bFlag;
    }

    //============================================================================

    // Purpose : Verify object exists or not
    // Author : Veena Katiyar
    public static boolean objectExists(String objectProperty,String propValue){
        boolean bFlag = false;
        try{
            bFlag=objectDisplayed(objectProperty, propValue);
            assertEquals(bFlag,true);
        }catch (Exception e){
            ExceptionHandler.printMsg("objectExists",e);
        }
        return bFlag;
    }
}
