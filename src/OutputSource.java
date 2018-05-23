import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public enum OutputSource {
    FILE("-of") {
        @Override
        public BufferedWriter print(Configuration configuration) throws IOException {
            return new BufferedWriter(new FileWriter(configuration.getOutputSourceData()));
        }
    }, CONSOLE("-oc") {
        @Override
        public BufferedWriter print(Configuration configuration) {
            return new BufferedWriter(new OutputStreamWriter(System.out));
        }
    };
    private String value;

    OutputSource(String value) {
        this.value = value;
    }

    public static OutputSource get(String value) {
        OutputSource[] values = OutputSource.values();
        for (OutputSource outputSource : values) {
            if (value.equals(outputSource.value)) {
                return outputSource;
            }
        }
        return null;
    }

    public abstract BufferedWriter print(Configuration configuration) throws IOException;
}
