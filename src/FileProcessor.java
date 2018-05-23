import com.ramesh.matcher.custom.StringMatcher;

import java.io.*;

public class FileProcessor {
    public BufferedWriter process(StringMatcher matcher, String fileName, Configuration configuration) throws IOException {
        BufferedWriter bufferedWriter = configuration.getOutputSource().print(configuration);
        FileInputStream inputFile = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
        String text;
        int count=0;
        while ((text = reader.readLine()) != null) {
            if (matcher.isMatched(text)) {
                if(configuration.getOutputType()==OutputType.LIST_COUNT){
                    count++;
                }
                else{
                    bufferedWriter.write(text);
                    bufferedWriter.newLine();
                }
            }
        }
        if(bufferedWriter==null){
            bufferedWriter.write(Integer.toString(count));
        }
        return bufferedWriter;
    }

}