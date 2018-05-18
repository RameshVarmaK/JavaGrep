import com.ramesh.matcher.custom.AutomataBasedMatcher;

import java.io.IOException;

public class Tester {
        public static void main(String[] args) throws IOException {
            AutomataBasedMatcher matcher=new AutomataBasedMatcher("a*b");
            System.out.println(matcher.isMatched("aaaaab"));
//        FileProcessor fp = new FileProcessor();
//        fp.process("/home/rameshv/IdeaProjects/JavaGrep/src/input.txt");

        }

}
