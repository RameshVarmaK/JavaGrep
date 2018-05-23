import com.ramesh.matcher.custom.DirectoryProcessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Tester {
    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sample1.txt"));
        bufferedWriter.write("uebf");
        bufferedWriter.write("sad");
        bufferedWriter.newLine();
        bufferedWriter.write("hyb");
        bufferedWriter.flush();
        DirectoryProcessor dp = new DirectoryProcessor();
        dp.process("/home/rameshv/IdeaProjects/JavaGrep/src/test");


    }

}
