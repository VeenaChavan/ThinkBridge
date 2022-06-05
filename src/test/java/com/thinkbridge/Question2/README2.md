
# JabaTalks Automation
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [**Environment**](#environment)
- [**Frameworks/Libraries**](#frameworkslibraries)
- [**How To Use**](#how-to-use)
   - [**Configuration Files**](#configuration-files)
   - [**Run Tests With Gradle**](#run-tests-with-gradle)
      - [**Perform On Browsers**](#perform-on-browsers)
      - [**Filter Tests**](#filter-tests)
   - [**Allure Report**](#allure-report)
      - [**Overview**](#overview)
      - [**Categories**](#categories)
      - [**Behaviors**](#behaviors)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## **Environment**
> **Gradle**: <em>6.8.2</em><br/>
> **Java**: <em>1.8.0_282</em><br/>
> **IDE**: <em>IntelliJ IDEA 2020.3 (Community Edition)</em><br/>

## **Frameworks/Libraries**
> **Selenide**: <em>5.6.1 - Web Driver</em><br/>
> **JUnit5 - Platform**: <em>1.6.0 - Testing Launcher</em><br/>
> **JUnit5 - Jupiter**: <em>5.6.0 - Testing Framework</em><br/>
> **JUnit5 - Vintage**: <em>5.6.0 - Test Engine</em><br/>
> **Allure**: <em>2.13.8 - Test Report</em><br/>
> **Allure Gradle**: <em>2.8.1 - Gradle Plugin</em><br/>

### **Configuration Files**
You can change values for your case.

1. [junit-platform.properties](src/test/resources/junit-platform.properties)<br/>
   > If a properties file with this name is present in the root of the classpath, it will be used as a source for configuration parameters. If multiple files are present, only the first one detected in the classpath will be used.
2. [allure.properties](src/test/resources/allure.properties)<br/>
   > Configuring all Allure properties by passing classpath.
3. [categories.json](src/test/resources/categories.json) for Allure Report<br/>
   > Defining categories to group tests.<br/>
   > **Category structure:**<br/>
   > ```json
    > {
    >   "name": "{Category name}",
    >   "matchedStatuses": "{One or more statuses of tests}",
    >   "traceRegex": "{Exception name}",
    >   "messageRegex": "{The content or description of Exception}"
    > }
    > ```
   > **Define a category by the following:**<br/>
   > - `matchedStatuses` -> The status of test, one in `skipped`, `passed`, `broken`, `failed`, `unknown`<br/>
   > - `traceRegex` -> Find the exception caused status to the test<br/>
   > - `messageRegex` -> Get content or description shortly or fully<br/>
   > - `name` -> Set category name based on above keys<br/>

### **Run Tests With Gradle**
> `./gradlew clean test allureReport`<br/>

Task `allureReport`: Build report from `thinkbridge-automation\build\allure-results` folder

#### **Perform On Browsers**
- chrome

Able to select browser by passing system property `selenide.browser`<br/>
> `./gradlew clean test -Dselenide.browser=chrome allureReport`

#### **Filter Tests**
You can filter tests by using option `--tests`<br/>
Giving values can be `TestPackage`, `TestClass`, `TestMethod`
> `./gradlew clean test -Dselenide.browser=chrome --tests ExampleTest.passedTest allureReport`

### **Allure Report**
<em>`Note`: If your test command contains task `allureReport`, when tests finished you can see below message in terminal likes.</em>
> **> Task :allureReport**<br/>
> Report successfully generated to D:\thinkbridge-automation\build\reports\allure-report<br/>

Open your browser with above path by Firefox<br/>
<em>`Note`: If you see Allure report is displayed likes below image.</em>
![](images/allure-loading-firefox.png?raw=true)
<em>Then you have to do by the following:.</em>
- Go to `about:config` in new tab
- Search for `privacy.file_unique_origin`
- Finally, change value to `false`, and refresh tab `Allure Report`

<em>Otherwise, you can see below.</em>

#### **Overview**
![](images/AllureReport.png?raw=true)

#### **Categories**
![](images/Categories.png?raw=true)

#### **Behaviors**
![](images/Behaviours.png?raw=true)

#### **Graphs**
![](images/Graphs.png?raw=true)

# Question 2
Question 2:
One of the travel websites requires you to automate their Flight Search function.
Automation Requirements:
 Launch a new Browser.
 Open URL http://jt-dev.azurewebsites.net/#/SignUp
 Validate that the dropdown has "English" and "Dutch"
 Fill in your name.
 For organization, use your name as well.
 Input your email address.
 Click on "I agree to the Terms And Conditions"
 Click on "SignUp"
 Validate that you received an email.
Deliverable:
The deliverable shall be shared via GitHub, BitBucket, SourceTree, or any versioning System
containing all relevant Test Cases in the correct sequence.
Evaluation and Scoring:
You are free to ask any questions for clarification or help.
You will be evaluated on the below attributes -
1. A working codebase delivered via Github, Gitlab, or Bitbucket.
2. 'Readme.md’ on the codebase, explaining how to run the deliverable.
3. Test Case Completeness of Requirements.
4. Automation Code Readability.
5. Use of Advance Technical concepts.
6. Tracking or Journaling your work to tell how you have spent your time on this problem.
   Next Steps:
7. Read the requirement thoroughly. Please keep a copy of it for a reference if needed.
8. Send the deliverable to rashmi.koravi@thinkbridge.in in the next 1 week via GitHub,
   BitBucket, or SourceTree. If not received within the specified time, we will consider it as an
   un-attempted question.
9. Click on the Submit button of this test.

**Solution :**

10. Test cases have been written in "JabaTalksTestCases.xlsx" file.
11. Testcases which needs to be automated have been marked with "Yes" in "To Be Automated?" column.
12. Testcases which have been automated are marked as "Yes" in the "Automation done ?" column.
13. Application specific reusable functions are placed in the "JabaTalks" class.
14. Yahoo mail has been used as a Email service provider. Functions related to Yahoo have been placed int he "YahooMail" class.
15. All the images have been placed in the "img" folder.
16. Test scripts are placed in "testcases" folder in "JabaTalksTest" class.
17. Comments are provided in the code for better understanding.
18. Screen shots of the test report is present in "report" folder.
19. Test data has been placed in the "testdata" folder.
20. BaseTest is the base class for all the testcase classes. It has functionality to upload Test cases information and test data. As well as creating report.
21. Allure report has been used for report creation.
22. JUnit5 has been used for executing testcases.
23. Fillo has been used to read test data from xls files.
24. To run the testcases , goto Question2 > testcases > JabaTalksTest java class.
19. Right click and select "Run JabaTalksTest" .

![](images/Execution.png?raw=true)

🕔 **Time Tracking :**

Understanding UI & functionality - 3 hours , 
Writing test cases - 3 hours , 
Automation - 8 hours

	

