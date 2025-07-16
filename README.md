![Rest Assured Framework Logo](https://github.com/user-attachments/assets/946f5e3a-b119-49cf-ac39-73584ba92753)

This is a Selenium with RestAssured Automation framework just in case I need to use one quick and I don't want to be setting up all from scratch.

## Technologies 👾
- Java
- Selenium
- Rest Assured
- MSEdge Web Driver
- JUnit
- Allure Reports

## Installing this framework 💾
- Clone the project
- Install your preferred JDK (17 or above)
- Under System Variable, create a new variable named "CLASSPATH" with route to the JDK binary. Example: _"C:\Program Files\Java\jdk-17\bin"_
- Under System Variables, edit "Path" and add a New one. Example: _"C:\Program Files\Java\jdk-17\bin"_
- Take into account this specific framework is only currently configured for MS Edge Browser so you might have to download and copy the latest `msedgedriver.exe` into the corresponding src/main/resources folder.

⚠️ This framework uses Rest Assured. By default, every request response is printed out to console. To avoid this, I disabled console output in `MyTestListener`. More details in there. Take into account if you want to print to debug, you'll have to disable this, but if you do this your print would be mixed along with lots of requests responses. In a real life scenario with hundreds of tests this would be unreadable.

## Running Tests 🏃

- Download maven from the apache website. After you install it, add the `bin` directory fom maven, to the `PATH`. 
- From the IDE terminal run the following command `mvn clean test`
- _Generating Reports_. Execute `allure generate target/results/allure-results -o target/results/allure-report --clean --single-file`.
- Reports can be found in target/results/allure-report.

## Github Actions ✔️
- This project is set up for being run on Github Actions. Everytime there is a Pull Request or a Push to the main branch an Action will be run and test will be executed. The report along with the results can be found in [Github Pages](https://fragsman.github.io/rest-assured-framework/) for this project.
- For more information about how is Github Actions configured, check the folder `.github/worlkflows` and the file `maven.yml`.

## Troubleshooting 🔧
- If you encounter the following message `WARNING: Unable to find an exact match for CDP version 136, returning the closest version; found: 135; Please update to a Selenium version that supports CDP version 136`, update Edge Web Driver from microsoft and replace the one in `/src/main/resources`. Also you might need to update Selenium version in `pom.xml`.

## Additional Notes 🗒️
- I have tried to use _BoniGarcia WebDriverManager_ to auto-download the correct detected Web Driver. I tested thoroughly a full day using Edge Version 116 and found that it was downloading an incorrect driver version. In conclusion, I will avoid depending on a 3rd party library which is maintained by only one person.
