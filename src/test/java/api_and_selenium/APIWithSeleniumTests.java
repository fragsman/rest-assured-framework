package api_and_selenium;

import base.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class APIWithSeleniumTests extends BaseTest {

    @Test
    @Tag("browser")
    public void
    validatesSchemaInClasspath() {
        // Given
        //String json = ... // Greeting response

        // Then
        //assertThat(json, matchesJsonSchemaInClasspath("greeting-schema.json"));
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }
}
