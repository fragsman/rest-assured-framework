package support;

import java.io.OutputStream;
import java.io.PrintStream;

public class CustomPrintStream extends PrintStream {

    public CustomPrintStream() {
        super(new OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing, effectively discarding the output
            }
        });
    }
}
