import java.io.IOException;
import java.util.Scanner;

public class SimpleConsole {

    public static void main(String args[]) throws IOException {
        Configuration configuration = new Configuration();
        Scanner scanner = new Scanner(System.in);
        System.out.println("***welcome to SimpleConsole application to check pattern and text***");
        System.out.print("Enter the pattern : ");
        configuration.setPattern(scanner.nextLine());
        System.out.println("Enter type of matcher : -c for customMatcher and -j for javaMatcher");
        configuration.setMatcherType(Matcher.getNameForValue(scanner.nextLine()));
        System.out.println("Enter type of input : -f for file , -d for directory and  -t for text");
        configuration.setInputType(InputFormat.getNameForValue(scanner.nextLine()));
        System.out.println("Enter input source : ");
        configuration.setInputSource(scanner.nextLine());
        MatcherApp matcherApp=new MatcherApp();
        matcherApp.matcher(configuration);
    }
}