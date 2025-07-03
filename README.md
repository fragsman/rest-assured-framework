![Rest Assured Framework Logo](https://github.com/user-attachments/assets/afb5a812-bb9b-45de-8ee0-f90a2d473ddc)

This is a Selenium with RestAssured Automation framework just in case I need to use one quick and I don't want to be setting up all from scratch.

## Technologies 👾
- Java
- Selenium
- Rest Assured
- MSEdge Web Driver
- JUnit
- Extent Reports

⚠️ This framework uses Rest Assured. By default, every request response is printed out to console. To avoid this, I disabled console output in `MyTestListener`. More details in there. Take into account if you want to print to debug, you'll have to disable this, but if you do this your print would be mixed along with lots of requests responses. In a real life scenario with hundreds of tests this would be unreadable.