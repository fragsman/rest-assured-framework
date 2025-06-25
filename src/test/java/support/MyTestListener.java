package support;

import io.restassured.RestAssured;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import utils.EnvironmentManager;
import utils.Logger;

public class MyTestListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan){
        Logger.initLogFile();
        Logger.Info("testPlanExecutionStarted: Setting base URI for Rest Assured");
        RestAssured.baseURI = EnvironmentManager.getInstance().getBaseUri();
    }


    public void testPlanExecutionFinished(TestPlan testPlan) {
        Logger.Info("testPlanExecutionFinished: Closing log writer");
        Logger.closeWriter();
    }
}
