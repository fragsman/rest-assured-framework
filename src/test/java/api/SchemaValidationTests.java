package api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchemaValidationTests {

    @Test
    @Tag("pepe")
    public void prueba_callbacks(){
        System.out.println("Test callbacks ");
        String name = "pepe";
        assertEquals(1,2);
        assertEquals(name,"pepe");
    }
}
