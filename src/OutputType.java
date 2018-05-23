import com.ramesh.matcher.custom.StringMatcher;

import java.io.*;

public enum OutputType {
    LIST_COUNT("-lc"){
        @Override
        public void FileProcessor(StringMatcher matcher, String fileName, BufferedWriter bufferedWriter) throws IOException {
            FileInputStream inputFile = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
            String text;
            int count=0;
            while ((text = reader.readLine()) != null) {
                if (matcher.isMatched(text)) {
                    count++;
                }
            }
            bufferedWriter.write(Integer.toString(count));
            bufferedWriter.newLine();
        }
    }, LIST("-l") {
        @Override
        public void FileProcessor(StringMatcher matcher, String fileName, BufferedWriter bufferedWriter) throws IOException {
            FileInputStream inputFile = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
            String text;
            while ((text = reader.readLine()) != null) {
                if (matcher.isMatched(text)) {
                    bufferedWriter.write(text);
                    bufferedWriter.newLine();
                }
            }
        }
    };
    private String value;

    OutputType(String value) {
        this.value = value;
    }

    public static OutputType get(String value) {
        OutputType[] values = OutputType.values();
        for (OutputType outputType : values) {
            if (value.equals(outputType.value)) {
                return outputType;
            }
        }
        return null;
    }
    abstract public void FileProcessor(StringMatcher matcher, String fileName, BufferedWriter bufferedWriter) throws IOException;
}
