import com.ramesh.JavaGrep.MatcherApp;
import com.ramesh.JavaGrep.helpers.WriterException;
import com.ramesh.JavaGrep.options.*;
import com.ramesh.JavaGrep.utils.Configuration;
import com.ramesh.JavaGrep.utils.ConfigurationBuilder;

import java.io.IOException;
import java.util.Scanner;

public class SimpleConsole {

    public static void main(String args[]) throws Exception {
        ConfigurationBuilder configuration = new ConfigurationBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("***welcome to SimpleConsole application to check pattern and text***");
        System.out.print("Enter the pattern : ");
        configuration.setPattern(scanner.nextLine());
        System.out.println("Enter type of match : -c for customMatcher and -j for javaMatcher");
        configuration.setMatcherType(MatcherType.valueFor(scanner.nextLine()).get());
        System.out.println("Enter type of input : -f for file , -d for directory and  -t for text");
        configuration.setInputSource(InputSource.getNameForValue(scanner.nextLine()).get());
        System.out.println("Enter input source : ");
        configuration.setInputSourceData(scanner.nextLine());
        System.out.println("Enter output type -oc for console and -of for file : ");
        configuration.setOutputSourceType(OutputSourceType.valueFor(scanner.nextLine()).get());
        System.out.println("Enter the output format -l for listing and -lc for count : ");
        configuration.setOutputType(OutputType.get(scanner.nextLine()).get());
        MatcherApp matcherApp = new MatcherApp();
        matcherApp.match(configuration.buid());
    }
}