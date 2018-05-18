import java.io.IOException;

public class JavaGrep {

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setPattern(args[getPatternPosition(args)]);
        configuration.setMatcherType(getMatcher(args));
        configuration.setInputType(getInputType(args));
        configuration.setInputSource(args[getInputSource(args)]);
        MatcherApp matcherApp = new MatcherApp();
        matcherApp.matcher(configuration);

    }

    private static int getPatternPosition(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                return i + 1;
            }
        }
        return -1;
    }

    private static Matcher getMatcher(String[] args) {
        for (String arg : args) {
            if (Matcher.getNameForValue(arg) != null) {
                return Matcher.getNameForValue(arg);
            }
        }
        return null;
    }


    private static int getInputSource(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (InputFormat.getNameForValue(args[i]) != null) {
                return i + 1;
            }
        }
        return -1;
    }

    private static InputFormat getInputType(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (InputFormat.getNameForValue(args[i]) != null) {
                return InputFormat.getNameForValue(args[i]);
            }
        }
        return null;
    }

}