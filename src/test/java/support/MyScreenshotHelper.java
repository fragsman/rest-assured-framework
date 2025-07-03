package support;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.function.Function;

public class MyScreenshotHelper  implements TestExecutionExceptionHandler {

    private final Function<ExtensionContext, WebDriver> driverSupplier;

    public MyScreenshotHelper(Function<ExtensionContext, WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    //Whenever an exception is raised (usually when test fails) this method will intercept it and take a screenshot
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        final WebDriver webDriver = this.driverSupplier.apply(context);
        if (Objects.nonNull(webDriver)) {
            final String attachmentName = "Screenshot on failure (" + throwable.getClass().getSimpleName() + ")";
            attachPageScreenshot(webDriver, attachmentName);
        }

        throw throwable;
    }

    public void attachPageScreenshot(WebDriver webDriver, String name) {
        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment(name, new ByteArrayInputStream(screenshotBytes));
    }
}
