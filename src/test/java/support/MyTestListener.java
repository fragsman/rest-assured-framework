package support;

import io.restassured.RestAssured;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import utils.EnvironmentManager;
import utils.Logger;

import java.io.PrintStream;

public class MyTestListener implements TestExecutionListener {

    private static PrintStream originalOut;
    private static PrintStream originalErr;

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan){
        Logger.initLogFile();
        Logger.Info("testPlanExecutionStarted: Setting base URI for Rest Assured");
        RestAssured.baseURI = EnvironmentManager.getInstance().getBaseUri();
        disableConsoleOutput();
    }

    public void testPlanExecutionFinished(TestPlan testPlan) {
        Logger.Info("testPlanExecutionFinished: Closing log writer");
        Logger.closeWriter();
        enableConsoleOutput();
    }

    /**
     * This is a force measure. I will disable all System.out and System.err messages
     * to avoid RestAssured from printing out to console everytime it makes a request
     * or receives a response. It is annoying and make debugging harder.
     */
    private static void disableConsoleOutput(){
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new CustomPrintStream()); // Redirect System.out to a null stream
        System.setErr(new CustomPrintStream()); // Redirect System.err to a null stream
    }

    /**
     * I'll re-enable output to console. See method above.
     */
    private static void enableConsoleOutput(){
        System.setOut(originalOut); // Restore original System.out
        System.setErr(originalErr); // Restore original System.err
    }
}
