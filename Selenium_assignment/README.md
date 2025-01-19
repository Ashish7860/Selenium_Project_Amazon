# Selenium Assignment

This project is an automation testing suite for the e-commerce website **Amazon** using Selenium WebDriver and TestNG.

## Project Setup

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 8+: Make sure Java is installed on your system.
- Maven: Maven is required to manage dependencies.
- Eclipse IDE: Eclipse IDE should be installed for Java development.
- Chrome Driver: This project uses Chrome WebDriver to run tests on Chrome browser.
- TestNG: For test execution.
- Selenium WebDriver

### Dependencies

The project uses Maven for dependency management. The necessary dependencies for Selenium and TestNG are already included in the `pom.xml` file.


###Running Tests from Eclipse
- Import the project into Eclipse as a Maven project.
- Run the tests:
- Run Tests from Eclipse
- Right-click on the src/test/java folder or individual test class.
- Select Run As > TestNG Test.

This will execute the tests defined in your project, such as:

- HomePageTest
- LoginTest

- Right-click on the testng.xml file.
- Select Run As -> TestNG Suite.

##Report Generation
TestNG provides built-in report generation. After the tests are executed, you can view the results in the test-output folder. The file testng-results.html contains the test execution details, including pass/fail results.

####Notes
Ensure the ChromeDriver is compatible with your installed Chrome version.
If you face issues with WebDriverManager, try manually setting the path to the WebDriver in your test code.

####Troubleshooting
WebDriver not found error: Ensure the path to ChromeDriver is correct.
Test fails due to slow loading: Increase the globalWaitTime in config.properties to allow more time for page elements to load.
